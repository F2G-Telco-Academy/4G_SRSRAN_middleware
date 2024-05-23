package com.f2g.middleware.domain.enb.report.service;

import com.f2g.middleware.domain.enb.report.model.Report;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class ReportService {

    @Value("file:${enb_report.file.path}")
    private Resource jsonFile;

    public List<Report> getReport() {
        ObjectMapper mapper = new ObjectMapper();
        List<Report> reports = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(jsonFile.getURI())));
            String[] jsons = content.split("(?<=})\\s+(?=\\{)");

            for (String json : jsons) {
                Report report = mapper.readValue(json, Report.class);
                reports.add(report);
            }
            log.info("Reports read!");
        } catch (IOException e) {
            log.error("Unable to read reports: {}", e.getMessage());
        }

        return reports;
    }

    public Report getLastReport() {
        ObjectMapper mapper = new ObjectMapper();
        Report lastReport = new Report();

        try (RandomAccessFile raf = new RandomAccessFile(jsonFile.getFile(), "r")) {
            long fileLength = raf.length() - 1;
            StringBuilder sb = new StringBuilder();
            int openBracesCount = 0;
            int closeBracesCount = 0;
            boolean isPreviousLineEndBrace = false;

            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                raf.seek(filePointer);
                int readByte = raf.readByte();

                if (readByte == '\n') {
                    if (!sb.isEmpty()) {
                        //String line = sb.reverse().toString();
                        String line = sb.toString();
                        openBracesCount = line.length() - line.replace("{", "").length();
                        closeBracesCount = line.length() - line.replace("}", "").length();

                        if (isPreviousLineEndBrace && line.trim().endsWith("}")) {
                            if (openBracesCount != (closeBracesCount - 1)) {
                                // Reset the counts and StringBuilder
                                openBracesCount = 0;
                                closeBracesCount = 0;
                                sb = new StringBuilder();
                                sb.append("}");
                            } else {
                                try {
                                    StringBuilder sb2 = new StringBuilder(sb);
                                    sb2.reverse();
                                    sb2.deleteCharAt(0);
                                    lastReport = mapper.readValue(sb2.toString(), Report.class);
                                    break;
                                } catch (IOException e) {
                                    // Ignore and continue
                                }
                            }
                        }


                        isPreviousLineEndBrace = line.trim().endsWith("{");
                    }
                } else if (readByte == '\r') {
                    // Ignore
                } else {
                    sb.append((char) readByte);
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to get last report: " + e.getMessage());
        }

        return lastReport;
    }



}
