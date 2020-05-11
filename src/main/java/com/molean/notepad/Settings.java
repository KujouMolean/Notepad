package com.molean.notepad;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(Settings.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFontName() {
        return properties.getProperty("fontName");
    }

    public static int getFontSize() {
        return Integer.parseInt(properties.getProperty("fontSize"));
    }

    public static int getFontStyle() {
        return Integer.parseInt(properties.getProperty("fontStyle"));
    }

    public static int getHeight() {

        return Integer.parseInt(properties.getProperty("height"));
    }

    public static int getWidth() {
        return Integer.parseInt(properties.getProperty("width"));
    }

    public static Font getFont() {
        return new Font(getFontName(), getFontStyle(), getFontSize());
    }

    public static int getTop() {
        return Integer.parseInt(properties.getProperty("top"));
    }

    public static int getLeft() {
        return Integer.parseInt(properties.getProperty("left"));
    }

    public static boolean getWordWrap() {
        return Boolean.parseBoolean(properties.getProperty("wordWrap"));
    }

    public static boolean getStatusBar() {
        return Boolean.parseBoolean(properties.getProperty("statusBar"));
    }

    public static void setWordWrap(boolean wordWrap) {
        properties.setProperty("wordWrap", wordWrap + "");
        store();
    }
    public static void setStatusBar(boolean statusBar) {
        properties.setProperty("statusBar", statusBar + "");
        store();
    }

    public static void setHeihgt(int height) {
        properties.setProperty("height", height + "");
        store();
    }

    public static void setWidth(int width) {
        properties.setProperty("width", width + "");
        store();
    }

    public static void setLeft(int left) {
        properties.setProperty("left", left + "");
        store();
    }

    public static void setTop(int top) {
        properties.setProperty("top", top + "");
        store();
    }

    private static void store() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Settings.class.getClassLoader().getResource("settings.properties").getPath());
            properties.store(fileOutputStream, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}