package org.daistudy.springframework.ioc.java.model;

public class WindowsShowCmd implements ShowCmd {
    @Override
    public String showCmd() {
        return "dir";
    }
}
