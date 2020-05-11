package com.molean.notepad;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import static com.molean.notepad.I18n.getMessage;
import static com.molean.notepad.Settings.*;

public class Notepad {
    private JFrame jFrame;
    private JTextArea jTextArea;
    private String[] args;
    private UndoManager undoManager;
    private JScrollPane jScrollPane;
    private String originText;
    private int scape;

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public int getScape() {
        return scape;
    }

    public void setScape(int scape) {
        this.scape = scape;
    }

    private boolean isChanged = false;

    private void initGraph() {
        try {
            UIManager.put("Menu.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("MenuItem.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("CheckBoxMenuItem.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("Label.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("TextField.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("CheckBox.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("Button.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("RadioButton.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.put("Border.font", new Font("Microsoft YaHei", Font.PLAIN, 12));
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jFrame = new JFrame(getTitle());
        jTextArea = new JTextArea(getText());
        undoManager = new UndoManager();
        jTextArea.setFont(getFont());
        jTextArea.getDocument().addUndoableEditListener(undoManager);
        jScrollPane = new JScrollPane(jTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBorder(null);
        jFrame.setIconImage(getIcon());
        jFrame.setBounds(getLeft(), getTop(), getWidth(), getHeight());
        jFrame.add(jScrollPane);
        jFrame.setJMenuBar(new Menu(this).getMenuBar());
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        jTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                setChanged(true);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                setChanged(true);
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
            }
        });
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Operate.exit(Notepad.this);
            }
        });
    }

    private void updateTitle() {
        if (isChanged())
            jFrame.setTitle("*" + getTitle());
        else
            jFrame.setTitle(getTitle());
    }

    private String getText() {
        if (args.length == 0) {
            originText = "";
            return originText;
        }


        try {
            FileInputStream is = new FileInputStream(args[0]);
            originText = new String(is.readAllBytes());
            return originText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private Image getIcon() {
        URL icon = getClass().getClassLoader().getResource("NotepadIcon/256.png");
        ImageIcon imageIcon = new ImageIcon(icon);
        return imageIcon.getImage();
    }


    private String getTitle() {
        String title;
        if (args.length == 0) {
            title = getMessage("untitled") + " - " + getMessage("name");
        } else {
            File file = new File(args[0]);

            title = file.getName() + " - " + getMessage("name");
        }
        return title;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public Notepad(String[] args) {
        this.args = args;
        initGraph();
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
        updateTitle();
    }
}
