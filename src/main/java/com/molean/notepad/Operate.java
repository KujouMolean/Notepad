package com.molean.notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.molean.notepad.I18n.getMessage;

public class Operate {
    public static void newFile(Notepad notepad) {
        if (notepad.isChanged()) {
            String replacement;
            if (notepad.getArgs().length == 0) {
                replacement = getMessage("untitled");
            } else {
                File file = new File(notepad.getArgs()[0]);
                replacement = file.getName();
            }
            String replace = getMessage("exitWithoutSave").replace("%0", replacement);
            int choose = JOptionPane.showConfirmDialog(null, replace, getMessage("name"), JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                if (save(notepad)) {
                    notepad.getjTextArea().setText("");
                    notepad.setArgs(new String[0]);
                    notepad.setChanged(false);
                }
            } else if (choose == JOptionPane.NO_OPTION) {
                notepad.getjTextArea().setText("");
                notepad.setArgs(new String[0]);
                notepad.setChanged(false);
            }

        } else {
            notepad.getjTextArea().setText("");
            notepad.setArgs(new String[0]);
            notepad.setChanged(false);
        }
    }

    public static void undo(Notepad notepad) {
        System.out.println(notepad);
        notepad.getUndoManager().undo();
    }

    public static void redo(Notepad notepad) {
        notepad.getUndoManager().redo();
    }

    public static void cut(Notepad notepad) {
        notepad.getjTextArea().cut();
    }

    public static void copy(Notepad notepad) {
        notepad.getjTextArea().copy();

    }

    public static void paste(Notepad notepad) {
        notepad.getjTextArea().paste();
    }

    public static void insertTimeDate(Notepad notepad) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        int pos1 = notepad.getjTextArea().getSelectionStart();
        int pos2 = notepad.getjTextArea().getSelectionEnd();
        notepad.getjTextArea().replaceRange(date, pos1, pos2);
    }

    public static boolean save(Notepad notepad) {
        String[] args = notepad.getArgs();
        if (args.length == 0)
            return saveAs(notepad);
        else {
            String text = notepad.getjTextArea().getText();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(notepad.getArgs()[0]);
                fileOutputStream.write(text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            notepad.setChanged(false);
        }
        return true;
    }

    public static boolean saveAs(Notepad notepad) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter(getMessage("txt"), "txt"));
        jFileChooser.showSaveDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null)
            return false;
        String fileName = selectedFile.getPath() + ".txt";
        String[] args = new String[1];
        args[0] = fileName;
        notepad.setArgs(args);

        
        String text = notepad.getjTextArea().getText();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        notepad.setChanged(false);
        return true;
    }
    public static boolean openFile(Notepad notepad){
        if (notepad.isChanged()) {
            String replacement;
            if (notepad.getArgs().length == 0) {
                replacement = getMessage("untitled");
            } else {
                File file = new File(notepad.getArgs()[0]);
                replacement = file.getName();
            }
            String replace = getMessage("exitWithoutSave").replace("%0", replacement);
            int choose = JOptionPane.showConfirmDialog(null, replace, getMessage("name"), JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                if (save(notepad)) {
                    open(notepad);
                }
            } else if (choose == JOptionPane.NO_OPTION) {
                open(notepad);
            }

        } else {
            open(notepad);
        }
        return true;
    }
    public static boolean open(Notepad notepad) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter(getMessage("txt"), "txt"));
        jFileChooser.showOpenDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null)
            return false;
        String[] args = new String[1];
        args[0] = selectedFile.getPath();
        notepad.setArgs(args);
        try {
            FileInputStream fileInputStream = new FileInputStream(selectedFile.getPath());
            notepad.getjTextArea().setText(new String(fileInputStream.readAllBytes()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        notepad.setChanged(false);
        return true;

    }

    public static void newWindow(Notepad notepad) {
        Boot.boot(new String[0]);
    }

    public static void exit(Notepad notepad) {
        Settings.setHeihgt(notepad.getjFrame().getHeight());
        Settings.setWidth(notepad.getjFrame().getWidth());
        Settings.setLeft(notepad.getjFrame().getX());
        Settings.setTop(notepad.getjFrame().getY());
        Settings.setWordWrap(notepad.getjTextArea().getLineWrap());
        if (notepad.isChanged()) {
            String replacement;
            if (notepad.getArgs().length == 0) {
                replacement = getMessage("untitled");
            } else {
                File file = new File(notepad.getArgs()[0]);
                replacement = file.getName();
            }
            String replace = getMessage("exitWithoutSave").replace("%0", replacement);
            int choose = JOptionPane.showConfirmDialog(null, replace, getMessage("name"), JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                if (save(notepad)) {
                    notepad.getjFrame().dispose();
                    Boot.die();
                }
            } else if (choose == JOptionPane.NO_OPTION) {
                notepad.getjFrame().dispose();
                Boot.die();
            }

        } else {
            notepad.getjFrame().dispose();
            Boot.die();
        }



    }

    public static void delete(Notepad notepad) {
        int selectionStart = notepad.getjTextArea().getSelectionStart();
        int selectionEnd = notepad.getjTextArea().getSelectionEnd();
        notepad.getjTextArea().replaceRange("", selectionStart, selectionEnd);

    }

    public static void searchWithBing(Notepad notepad) {
        String tobeSearch="https://www.bing.com/search?q="+ URLEncoder.encode( notepad.getjTextArea().getSelectedText());
        try{
            java.net.URI uri = java.net.URI.create(tobeSearch);
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void find(Notepad notepad, String text, boolean matchCase, boolean wrapAround, boolean downSearch,int selectonStart,int selectionEnd) {
        String source  = notepad.getjTextArea().getText();
        if(downSearch){
            String sub = source.substring(selectionEnd,source.length());
            if(matchCase==false){
                sub=sub.toLowerCase();
                text=text.toLowerCase();
            }
            int pos = sub.indexOf(text.toLowerCase());
            if(pos==-1){
                if(wrapAround){
                    find(notepad,text,matchCase,false,true,0,0);
                }else{
                    String notFound = getMessage("notFound").replace("%0",text);
                    JOptionPane.showMessageDialog(null,notFound,getMessage("name"),JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                notepad.getjTextArea().setSelectionStart(selectionEnd+pos);
                notepad.getjTextArea().setSelectionEnd(selectionEnd+pos+text.length());
            }

        }else{
            String sub = source.substring(0,selectonStart);
            if(matchCase==false){
                sub=sub.toLowerCase();
                text=text.toLowerCase();
            }
            int pos = sub.lastIndexOf(text.toLowerCase());
            if(pos==-1){
                if(wrapAround){
                    find(notepad,text,matchCase,false,false,source.length(),source.length());
                }else{
                    String notFound = getMessage("notFound").replace("%0",text);
                    JOptionPane.showMessageDialog(null,notFound,getMessage("name"),JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                notepad.getjTextArea().setSelectionStart(pos);
                notepad.getjTextArea().setSelectionEnd(pos+text.length());
            }
        }

    }

    public static void selectAll(Notepad notepad) {
        notepad.getjTextArea().setSelectionStart(0);
        notepad.getjTextArea().setSelectionEnd(notepad.getjTextArea().getText().length());
    }

    public static void font(Notepad notepad) {
//        new
    }

    public static void wordWrap(Notepad notepad, boolean isSelected) {
        if(isSelected){
            notepad.getjTextArea().setLineWrap(true);
            notepad.getjScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        }else{
            notepad.getjTextArea().setLineWrap(false);
        }
    }

    public static void sendFeedback(Notepad notepad) {
        String url="https://github.com/cnMolean/Notepad/issues";
        try{
            java.net.URI uri = java.net.URI.create(url);
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewHelp(Notepad notepad) {
        String url="https://github.com/cnMolean/Notepad";
        try{
            java.net.URI uri = java.net.URI.create(url);
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
