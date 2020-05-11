package com.molean.notepad;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static com.molean.notepad.I18n.getMessage;

public class Find {
    private final JFrame jFrame = new JFrame(getMessage("find"));
    private final JTextField findWhatTextField = new JTextField();
    private final JCheckBox matchCaseCheckBox = new JCheckBox(getMessage("matchCase"));
    private final JCheckBox wrapArountCheckBox = new JCheckBox(getMessage("wrapAround"));
    private final JRadioButton downButton = new JRadioButton(getMessage("down"));
    private final Notepad notepad;
    public Find(Notepad notepad){
        this.notepad=notepad;
        jFrame.setLayout(null);
        jFrame.setSize(366, 152);

        JLabel findWhatLabel = new JLabel(getMessage("findWhat"));
        findWhatLabel.setSize(63, 13);
        findWhatLabel.setLocation(8, 8);


        findWhatTextField.setSize(188, 16);
        findWhatTextField.setLocation(70, 8);


        JButton findNextButton = new JButton(getMessage("findNext"));
        findNextButton.setSize(75, 23);
        findNextButton.setLocation(270, 8);


        JButton cancelButton = new JButton(getMessage("cancel"));
        cancelButton.setSize(75, 23);
        cancelButton.setLocation(270, 37);


        matchCaseCheckBox.setSize(96, 20);
        matchCaseCheckBox.setLocation(8, 65);


        wrapArountCheckBox.setSize(96, 20);
        wrapArountCheckBox.setLocation(8, 85);

        JPanel directionPanel = new JPanel();

        TitledBorder directionBorder = BorderFactory.createTitledBorder(getMessage("direction"));
        directionBorder.setTitleFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        directionPanel.setBorder(directionBorder);

        directionPanel.setSize(130, 70);
        directionPanel.setLocation(130, 30);


        JRadioButton upButton = new JRadioButton(getMessage("up"));
        upButton.setSize(38, 20);
        upButton.setLocation(0, 0);

        downButton.setSize(38, 20);
        downButton.setLocation(40, 0);
        downButton.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(upButton);
        buttonGroup.add(downButton);

        directionPanel.add(upButton);
        directionPanel.add(downButton);


        jFrame.add(directionPanel);
        jFrame.add(findWhatLabel);
        jFrame.add(findWhatTextField);
        jFrame.add(findNextButton);
        jFrame.add(cancelButton);
        jFrame.add(matchCaseCheckBox);
        jFrame.add(wrapArountCheckBox);
        jFrame.setResizable(false);

        cancelButton.addActionListener((e)-> jFrame.setVisible(false));

        findNextButton.addActionListener((e) -> {
                    int selectionStart = notepad.getjTextArea().getSelectionStart();
                    int selectionEnd = notepad.getjTextArea().getSelectionEnd();
                    Operate.find(
                            notepad, findWhatTextField.getText(),
                            matchCaseCheckBox.isSelected(),
                            wrapArountCheckBox.isSelected(),
                            downButton.isSelected(),
                            selectionStart,
                            selectionEnd
                    );
                }
        );
    }
    public void show(String selectedText){
        if(selectedText!=null)
            findWhatTextField.setText(selectedText);
        jFrame.setVisible(true);
    }
    public void next(){
        int selectionStart = notepad.getjTextArea().getSelectionStart();
        int selectionEnd = notepad.getjTextArea().getSelectionEnd();
        Operate.find(
                notepad, findWhatTextField.getText(),
                matchCaseCheckBox.isSelected(),
                wrapArountCheckBox.isSelected(),
                true,
                selectionStart,
                selectionEnd
        );
    }
    public void previous(){
        int selectionStart = notepad.getjTextArea().getSelectionStart();
        int selectionEnd = notepad.getjTextArea().getSelectionEnd();
        Operate.find(
                notepad, findWhatTextField.getText(),
                matchCaseCheckBox.isSelected(),
                wrapArountCheckBox.isSelected(),
                false,
                selectionStart,
                selectionEnd
        );
    }
}
