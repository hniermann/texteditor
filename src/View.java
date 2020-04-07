import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 *
 * @author Henry Niermann
 *
 */
public class View extends JFrame implements ActionListener, DocumentListener {

    JFrame outline;

    JTextArea text;

    JMenuItem save;

    JMenuItem saveAs;

    JMenu fileMenu;

    JTextPane name;

    JTextPane pathway;

    Controller control;

    JButton saveButton;

    public View() {
        //Create outline
        this.outline = new JFrame("*Unnamed");
        this.outline.setBounds(100, 100, 500, 300);

        //Create text area
        this.text = new JTextArea();
        this.text.getDocument().addDocumentListener(this);

        //Create menu bar
        JMenuBar menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        menuBar.add(this.fileMenu);

        //Create menu functionality
        this.save = new JMenuItem("Save");
        this.save.addActionListener(this);
        this.fileMenu.add(this.save);

        this.saveAs = new JMenuItem("Save as...");
        this.saveAs.addActionListener(this);
        this.fileMenu.add(this.saveAs);

        this.registerObserver(this.control);

        //Put everything together
        this.outline.setJMenuBar(menuBar);
        this.outline.add(this.text);
        this.outline.setVisible(true);
    }

    public JTextArea getText() {
        return this.text;
    }

    public void registerObserver(Controller controller) {
        this.control = controller;
    }

    public void changeDocName(String name) {
        this.outline.setTitle(name);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == this.save) {
            this.control.saveEvent();
        }
        if (source == this.saveAs) {
            this.control.saveAsWindowEvent();
        }
        if (source == this.saveButton) {
            this.control.saveAsConfirmedEvent();
        }
        if (source == this.text) {
            this.control.unsavedTextEvent();
        }
    }

    public void queueName() {
        JFrame nameWindow = new JFrame();

        GridLayout grid = new GridLayout(4, 1);
        nameWindow.setLayout(grid);

        JLabel queue = new JLabel("File Name:");
        this.name = new JTextPane();

        JLabel path = new JLabel("Path:");
        this.pathway = new JTextPane();

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(this);

        nameWindow.add(queue);
        nameWindow.add(this.name);
        nameWindow.add(path);
        nameWindow.add(this.pathway);
        nameWindow.add(this.saveButton);

        nameWindow.setVisible(true);

    }

    public String getNewName() {
        return this.name.getText();
    }

    public String getNewPath() {
        return this.pathway.getText();
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        Object source = event.getDocument();
        if (source == this.text.getDocument()) {
            this.control.unsavedTextEvent();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        Object source = event.getDocument();
        if (source == this.text.getDocument()) {
            this.control.unsavedTextEvent();
        }

    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        Object source = event.getDocument();
        if (source == this.text.getDocument()) {
            this.control.unsavedTextEvent();
        }
    }

}
