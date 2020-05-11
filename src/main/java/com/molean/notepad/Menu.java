package com.molean.notepad;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static com.molean.notepad.I18n.getMessage;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

public class Menu {
    private JMenuBar jMenuBar;
    private Notepad notepad;
    private Find find;

    public Menu(Notepad notepad) {
        this.notepad = notepad;
        this.find = new Find(notepad);
    }

    public JMenuBar getMenuBar() {
        jMenuBar = new JMenuBar();
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setBorderPainted(false);


        JMenu fileMenu = jMenuBar.add(new JMenu(getMessage("file")));

        JMenuItem newItem = fileMenu.add(getMessage("new"));
        JMenuItem newWindowItem = fileMenu.add(getMessage("newWindow"));
        JMenuItem openItem = fileMenu.add(getMessage("open"));
        JMenuItem saveItem = fileMenu.add(getMessage("save"));
        JMenuItem saveAsItem = fileMenu.add(getMessage("saveAs"));
        fileMenu.addSeparator();
        JMenuItem exitItem = fileMenu.add(getMessage("exit"));

        JMenu editMenu = jMenuBar.add(new JMenu(getMessage("edit")));
        JMenuItem undoItem = editMenu.add(getMessage("undo"));
        editMenu.addSeparator();
        JMenuItem cutItem = editMenu.add(getMessage("cut"));
        JMenuItem copyItem = editMenu.add(getMessage("copy"));
        JMenuItem pasteItem = editMenu.add(getMessage("paste"));
        JMenuItem deleteItem = editMenu.add(getMessage("delete"));
        editMenu.addSeparator();
        JMenuItem searchWithBingItem = editMenu.add(getMessage("searchWithBing"));
        JMenuItem findItem = editMenu.add(getMessage("find"));
        JMenuItem findNextItem = editMenu.add(getMessage("findNext"));
        JMenuItem findPreviousItem = editMenu.add(getMessage("findPrevious"));
        JMenuItem replaceItem = editMenu.add(getMessage("replace"));
        JMenuItem goToItem = editMenu.add(getMessage("goTo"));
        editMenu.addSeparator();
        JMenuItem selectAllItem = editMenu.add(getMessage("selectAll"));
        JMenuItem timeDateItem = editMenu.add(getMessage("timeDate"));


        JMenu formatMenu = jMenuBar.add(new JMenu(getMessage("format")));
        JCheckBoxMenuItem wordWrapItem = new JCheckBoxMenuItem(getMessage("wordWrap"));
        formatMenu.add(wordWrapItem);
        JMenuItem fontItem = formatMenu.add(getMessage("font"));

        JMenu viewMenu = jMenuBar.add(new JMenu(getMessage("view")));
        JMenu zoomMenu = new JMenu(getMessage("zoom"));
        viewMenu.add(zoomMenu);
        JMenuItem zoomInItem = zoomMenu.add(getMessage("zoomIn"));
        JMenuItem zoomOutItem = zoomMenu.add(getMessage("zoomOut"));
        JMenuItem restoreDefaultZoomItem = zoomMenu.add(getMessage("restoreDefaultZoom"));
        JCheckBoxMenuItem statusBarItem = new JCheckBoxMenuItem(getMessage("statusBar"));
        viewMenu.add(statusBarItem);


        JMenu helpMenu = jMenuBar.add(new JMenu(getMessage("help")));
        JMenuItem viewHelpItem = helpMenu.add(getMessage("viewHelp"));
        JMenuItem sendFeedbackItem = helpMenu.add(getMessage("sendFeedback"));
        helpMenu.addSeparator();
        JMenuItem aboutNotepadItem = helpMenu.add(getMessage("aboutNotepad"));


        newItem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
        newWindowItem.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        undoItem.setAccelerator(KeyStroke.getKeyStroke('Z', CTRL_DOWN_MASK));
        cutItem.setAccelerator(KeyStroke.getKeyStroke('X', CTRL_DOWN_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke('V', CTRL_DOWN_MASK));
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        searchWithBingItem.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
        findItem.setAccelerator(KeyStroke.getKeyStroke('F', CTRL_DOWN_MASK));
        findNextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        findPreviousItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.SHIFT_DOWN_MASK));
        replaceItem.setAccelerator(KeyStroke.getKeyStroke('H', CTRL_DOWN_MASK));
        goToItem.setAccelerator(KeyStroke.getKeyStroke('G', CTRL_DOWN_MASK));
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke('A', CTRL_DOWN_MASK));
        timeDateItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.SHIFT_DOWN_MASK));
        zoomOutItem.setAccelerator(KeyStroke.getKeyStroke('-', CTRL_DOWN_MASK));
        restoreDefaultZoomItem.setAccelerator(KeyStroke.getKeyStroke('0', CTRL_DOWN_MASK));

        newItem.addActionListener(this::newFile);
        newWindowItem.addActionListener(this::newWindow);
        openItem.addActionListener(this::openFile);
        saveItem.addActionListener(this::saveFile);
        saveAsItem.addActionListener(this::saveAs);
        exitItem.addActionListener(this::exitNotepad);

        undoItem.addActionListener(this::undo);
        cutItem.addActionListener(this::cut);
        copyItem.addActionListener(this::copy);
        pasteItem.addActionListener(this::paste);
        deleteItem.addActionListener(this::delete);
        searchWithBingItem.addActionListener(this::searchWithBing);
        findItem.addActionListener(this::find);
        findNextItem.addActionListener(this::findNext);
        findPreviousItem.addActionListener(this::findPrevious);
//        replaceItem.addActionListener(actionEvent -> replace(actionEvent));
        replaceItem.setEnabled(false);
//        goToItem.addActionListener(actionEvent -> goTo(actionEvent));
        goToItem.setEnabled(false);
        selectAllItem.addActionListener(this::selectAll);
        timeDateItem.addActionListener(this::timeDate);
//
//

        wordWrapItem.addActionListener((event)->{wordWrap(wordWrapItem.isSelected());});
        fontItem.addActionListener(this::font);
//        zoomInItem.addActionListener(actionEvent -> zoomIn(actionEvent));
        zoomMenu.setEnabled(false);
//        zoomOutItem.addActionListener(actionEvent -> zoomOut(actionEvent));
//        restoreDefaultZoomItem.addActionListener(actionEvent -> restoreDefaultZoom(actionEvent));
//        statusBarItem.addActionListener(actionEvent -> statusBar(actionEvent));
//        viewHelpItem.addActionListener(actionEvent -> viewHelp(actionEvent));
//        sendFeedbackItem.addActionListener(actionEvent -> sendFeedback(actionEvent));
        aboutNotepadItem.addActionListener(actionEvent -> aboutNotepad(actionEvent));

        editMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent menuEvent) {
                undoItem.setEnabled(notepad.getUndoManager().canUndo());
                cutItem.setEnabled(notepad.getjTextArea().getSelectedText() != null);
                copyItem.setEnabled(notepad.getjTextArea().getSelectedText() != null);
                deleteItem.setEnabled(notepad.getjTextArea().getSelectedText() != null);
                searchWithBingItem.setEnabled(notepad.getjTextArea().getSelectedText() != null);
                goToItem.setEnabled(false);
                if (notepad.getjTextArea().getText().equals("")) {
                    findItem.setEnabled(false);
                    findNextItem.setEnabled(false);
                    findPreviousItem.setEnabled(false);
                } else {
                    findItem.setEnabled(true);
                    findNextItem.setEnabled(true);
                    findPreviousItem.setEnabled(true);
                }


            }

            @Override
            public void menuDeselected(MenuEvent menuEvent) {

            }

            @Override
            public void menuCanceled(MenuEvent menuEvent) {

            }

        });

        return jMenuBar;
    }

    private void aboutNotepad(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null,"Design by Molean. GNU License. ");
    }

    private void font(ActionEvent actionEvent) {
        Operate.font(notepad);
    }

    private void wordWrap(boolean isSelected) {
        Operate.wordWrap(notepad,isSelected);
    }

    private void selectAll(ActionEvent actionEvent) {
        Operate.selectAll(notepad);
    }

    private void findPrevious(ActionEvent actionEvent) {
        find.previous();
    }

    private void findNext(ActionEvent actionEvent) {
        find.next();
    }

    private void find(ActionEvent actionEvent) {
        find.show(notepad.getjTextArea().getSelectedText());

    }

    private void searchWithBing(ActionEvent actionEvent) {
        Operate.searchWithBing(notepad);
    }

    private void delete(ActionEvent actionEvent) {
        Operate.delete(notepad);

    }

    private void exitNotepad(ActionEvent actionEvent) {
        Operate.exit(notepad);
    }

    private void openFile(ActionEvent actionEvent) {
        Operate.openFile(notepad);
    }

    private void newWindow(ActionEvent actionEvent) {
        Operate.newWindow(notepad);
    }

    private void saveFile(ActionEvent actionEvent) {
        Operate.save(notepad);
    }

    private void saveAs(ActionEvent actionEvent) {
        Operate.saveAs(notepad);
    }

    private void paste(ActionEvent actionEvent) {
        Operate.paste(notepad);
    }

    private void copy(ActionEvent actionEvent) {
        Operate.copy(notepad);
    }

    private void cut(ActionEvent actionEvent) {
        Operate.cut(notepad);
    }

    private void undo(ActionEvent actionEvent) {
        Operate.undo(notepad);
    }

    private void timeDate(ActionEvent actionEvent) {
        Operate.insertTimeDate(notepad);
    }

    private void newFile(ActionEvent actionEvent) {
        Operate.newFile(notepad);
    }

}
