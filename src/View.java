import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
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

    JMenuItem open;

    JButton openButton;

    JMenu fileMenu;

    JTextPane pathname;

    JTextPane name;

    JTextPane pathway;

    Controller control;

    JButton saveButton;

    public View() {
        //Create outline
        this.outline = new JFrame("*Unnamed.txt");
        this.outline.pack();

        //Set outline location to center, set bounds to half screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.outline.setSize(width / 2, height / 2);
        this.outline.setLocationRelativeTo(null);

        //Create text area
        this.text = new JTextArea();
        this.text.getDocument().addDocumentListener(this);

        //Create scrolling area
        JScrollPane scroller = new JScrollPane(this.text,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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

        this.open = new JMenuItem("Open");
        this.open.addActionListener(this);
        this.fileMenu.add(this.open);

        this.registerObserver(this.control);

        //Put everything together
        this.outline.setJMenuBar(menuBar);
        this.outline.add(scroller);
        this.outline.setVisible(true);
        this.outline.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JTextArea getText() {
        return this.text;
    }

    public void writeText(List<String> text) {
        while (text.size() > 0) {
            String line = text.remove(0);
            this.text.append(line);
        }

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
        if (source == this.open) {
            this.queueOpen();
        }
        if (source == this.openButton) {
            this.control.openButtonEvent(this.pathname.getText());
        }
    }

    public void queueName() {
        JFrame nameWindow = new JFrame();
        nameWindow.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        nameWindow.setSize(width / 5, height / 5);
        nameWindow.setLocationRelativeTo(null);

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
        nameWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public void queueOpen() {
        JFrame openWindow = new JFrame();
        openWindow.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        openWindow.setSize(width / 5, height / 5);
        openWindow.setLocationRelativeTo(null);

        GridLayout grid = new GridLayout(3, 0);
        openWindow.setLayout(grid);

        JLabel queue = new JLabel("File Path:", SwingConstants.CENTER);

        this.pathname = new JTextPane();

        this.openButton = new JButton("Open");

        this.openButton.addActionListener(this);

        openWindow.add(queue);
        openWindow.add(this.pathname);
        openWindow.add(this.openButton);

        openWindow.setVisible(true);
        openWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
