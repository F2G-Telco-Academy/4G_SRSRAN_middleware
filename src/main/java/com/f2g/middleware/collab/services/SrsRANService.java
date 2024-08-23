package com.f2g.middleware.collab.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class SrsRANService {
    @Value("${sudo-password}")
    private String sudoPassword;

    private static final String SRSRAN_BUILD_DIR = "cd /home/salam/srsRAN/build";
    private static final long TIMEOUT_SECONDS = 10;

    public String startEPC() throws IOException, InterruptedException {
        return asyncExecuteCommand(SRSRAN_BUILD_DIR + " && nohup sudo ./srsepc/src/srsepc ~/.config/srsran/epc.conf");
    }

    public String startENB() throws IOException, InterruptedException {
        return asyncExecuteCommand(SRSRAN_BUILD_DIR + " && nohup sudo ./srsenb/src/srsenb ~/.config/srsran/enb.conf");
    }

    public String startUE() throws IOException, InterruptedException {
        return asyncExecuteCommand(SRSRAN_BUILD_DIR + " && nohup sudo ./srsue/src/srsue --rf.device_name=zmq --rf.device_args=\"tx_port=tcp://*:2001,rx_port=tcp://localhost:2000,id=ue,base_srate=23.04e6\" --gw.netns=ue1");
    }

    // STATUS OF SERVICES
    public boolean isEPCRunning() throws IOException {
        return !executeCommand("ps aux | grep srsepc | grep -v grep | awk '{print $2}'").isEmpty();
    }

    public boolean isENBRunning() throws IOException {
        return !executeCommand("ps aux | grep srsenb | grep -v grep | awk '{print $2}'").isEmpty();
    }

    public boolean isUERunning() throws IOException {
        return !executeCommand("ps aux | grep srsue | grep -v grep | awk '{print $2}'").isEmpty();
    }

    // STOPPING SERVICES
    public void stopEPC() throws IOException {
        String pids = executeCommand("ps aux | grep srsepc | grep -v grep | awk '{print $2}'");
        killProcesses(pids);
    }

    public void stopENB() throws IOException {
        String pids = executeCommand("ps aux | grep srsenb | grep -v grep | awk '{print $2}'");
        killProcesses(pids);
    }

    public void stopUE() throws IOException {
        String pids = executeCommand("ps aux | grep srsue | grep -v grep | awk '{print $2}'");
        killProcesses(pids);
    }

    private void killProcesses(String pids) throws IOException {
        if (!pids.isEmpty()) {
            String[] pidArray = pids.split("\n");
            for (String pid : pidArray) {
                executeCommand("sudo kill -9 " + pid);
            }
        }
    }

    public String asyncExecuteCommand(String command) throws IOException, InterruptedException {
        var os = System.getProperty("os.name");
        long TIMEOUT_SECONDS = 10;

        if (os.toLowerCase().contains("nux")) {
            ProcessBuilder processBuilder;

            if (command.toLowerCase().startsWith("sudo ") || command.toLowerCase().contains("sudo")) {
                processBuilder = new ProcessBuilder("bash", "-c", "echo '" + sudoPassword + "' | sudo -S bash -c '" + command + "'");
            } else {
                processBuilder = new ProcessBuilder("bash", "-c", command);
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) < TIMEOUT_SECONDS * 1000) {
                if (reader.ready()) { // Check if a line is available without blocking
                    line = reader.readLine();
                    if (line != null) {
                        output.append(line).append("\n");
                    }
                } else {
                    // Briefly sleep to avoid busy-waiting
                    Thread.sleep(100);
                }
            }

            return output.toString();
        } else {
            return "You are not running on a Linux system.";
        }
    }


    public String executeCommand(String command) throws IOException {
        var os = System.getProperty("os.name");

        if (os.toLowerCase().contains("nux")) {


            ProcessBuilder processBuilder;

            if (command.toLowerCase().startsWith("sudo ") || command.toLowerCase().contains("sudo")) {
                processBuilder = new ProcessBuilder("bash", "-c", "echo '" + sudoPassword + "' | sudo -S bash -c '" + command + "'");
            } else {
                // Handle non-sudo commands, including those with 'cd'
                processBuilder = new ProcessBuilder("bash", "-c", command);
            }

            processBuilder.redirectErrorStream(true); // Set redirectErrorStream outside the if-else

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ( (line = reader.readLine()) != null ) {
                output.append(line).append("\n");
            }

            return output.toString();
        } else {
            return "You are not running on a Linux system";
        }
    }
}
