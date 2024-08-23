package com.f2g.middleware.collab.dto;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfigObjectDTO {
    public String label;
    public String value;
    public boolean isActive;
    public String filename;
    public String section;
}