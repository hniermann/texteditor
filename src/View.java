import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 *
 *
 * @author Henry Niermann
 *
 */
public class View extends JFrame implements ActionListener {

    JFrame outline;

    JTextArea text;

    JMenuItem save;

    JMenu fileMenu;

    Controller control;

    public View() {
        //Create outline
        this.outline = new JFrame("*Unnamed");
        this.outline.setBounds(100, 100, 500, 300);

        //Create text area
        this.text = new JTextArea();

        //Create menu bar
        JMenuBar menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        menuBar.add(this.fileMenu);

        //Create menu functionality
        this.save = new JMenuItem("Save");
        this.save.addActionListener(this);
        this.fileMenu.add(this.save);

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
    }

}
