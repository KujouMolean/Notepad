package com.molean.notepad;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
    public static String getMessage(String key) {
//        Locale locale = new Locale("zh_CN");
        Locale locale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("message", locale);
        return messages.getString(key);

    }
}
