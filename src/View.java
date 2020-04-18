import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class View extends JFrame
        implements ActionListener, DocumentListener, WindowStateListener {

    JFrame outline;

    JTextArea text;

    JMenuItem save;

    JMenuItem saveAs;

    JMenuItem open;

    JButton openButton;

    JMenu fileMenu;

    JTextPane pathname;

    JTextField name;

    JTextField pathway;

    Controller control;

    JButton saveButton;

    public View() {
        //Create outline
        this.outline = new JFrame("*Unnamed.txt");
        this.outline.addWindowStateListener(this);
        this.outline.pack();

        //Set outline location to center, set bounds to half screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.outline.setSize(width / 2, height / 2);
        this.outline.setLocationRelativeTo(null);

        //Create text area
        this.text = new JTextArea(15, 70);

        this.text.getDocument().addDocumentListener(this);
        this.text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.text.setLineWrap(true);

        //Create lines
        JTextArea lines = new JTextArea(15, 2);
        lines.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lines.setEditable(false);

        //Pack it all

        JSplitPane combo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lines,
                this.text);
        combo.setResizeWeight(0d / 10d);

        //Create scrolling area

        JScrollPane scroller = new JScrollPane(combo,
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
        JFrame frame = new JFrame("Save as");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width / 5, height / 5);
        frame.setLocationRelativeTo(null);

        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JLabel lblName = new JLabel("Name:");
        springLayout.putConstraint(SpringLayout.WEST, lblName, 21,
                SpringLayout.WEST, frame.getContentPane());
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        frame.getContentPane().add(lblName);

        JLabel lblPath = new JLabel("Path:");
        springLayout.putConstraint(SpringLayout.NORTH, lblName, 6,
                SpringLayout.SOUTH, lblPath);
        springLayout.putConstraint(SpringLayout.EAST, lblName, 0,
                SpringLayout.EAST, lblPath);
        springLayout.putConstraint(SpringLayout.WEST, lblPath, 34,
                SpringLayout.WEST, frame.getContentPane());
        lblPath.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        frame.getContentPane().add(lblPath);

        this.name = new JTextField();
        springLayout.putConstraint(SpringLayout.EAST, this.name, -86,
                SpringLayout.EAST, frame.getContentPane());
        this.name.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        frame.getContentPane().add(this.name);
        this.name.setColumns(10);

        this.saveButton = new JButton("Save");
        springLayout.putConstraint(SpringLayout.SOUTH, this.name, -21,
                SpringLayout.NORTH, this.saveButton);
        springLayout.putConstraint(SpringLayout.NORTH, this.saveButton, 79,
                SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, this.saveButton, 146,
                SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, this.saveButton, -10,
                SpringLayout.SOUTH, frame.getContentPane());
        this.saveButton.addActionListener(this);
        this.saveButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        frame.getContentPane().add(this.saveButton);

        this.pathway = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, this.name, 6,
                SpringLayout.SOUTH, this.pathway);
        springLayout.putConstraint(SpringLayout.NORTH, lblPath, 0,
                SpringLayout.NORTH, this.pathway);
        springLayout.putConstraint(SpringLayout.EAST, lblPath, -6,
                SpringLayout.WEST, this.pathway);
        springLayout.putConstraint(SpringLayout.WEST, this.name, 0,
                SpringLayout.WEST, this.pathway);
        springLayout.putConstraint(SpringLayout.NORTH, this.pathway, 10,
                SpringLayout.NORTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, this.pathway, 85,
                SpringLayout.WEST, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, this.pathway, -79,
                SpringLayout.SOUTH, frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, this.pathway, -86,
                SpringLayout.EAST, frame.getContentPane());
        this.pathway.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        this.pathway.setColumns(10);
        frame.getContentPane().add(this.pathway);

        frame.setVisible(true);

    }

    public String getNewName() {
        return this.name.getText();
    }

    public String getNewPath() {
        return this.pathway.getText();
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

    @Override
    public void windowStateChanged(WindowEvent e) {
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            System.out.print("maximized");

        }

    }

}
