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
public class View extends JFrame {

    JTextArea text;

    public View() {
        //Create outline
        JFrame outline = new JFrame("*Unnamed");
        outline.setBounds(100, 100, 500, 300);

        //Create text area
        this.text = new JTextArea();

        //Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //Create menu functionality
        JMenuItem save = new JMenuItem("Save");
        fileMenu.add(save);
        JMenuItem saveAs = new JMenuItem("Save as");
        fileMenu.add(saveAs);

        //Put everything together
        outline.setJMenuBar(menuBar);
        outline.add(this.text);
        outline.setVisible(true);
    }

}
