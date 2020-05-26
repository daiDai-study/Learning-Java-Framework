package org.daistudy.springframework.ioc.java.model;

public class LinuxShowCmd implements ShowCmd {
    @Override
    public String showCmd() {
        return "ls";
    }
}
