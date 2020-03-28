import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 *
 * @author Henry Niermann
 *
 */
public class View extends JFrame {

    public View() {
        JFrame outline = new JFrame();
        outline.setBounds(100, 100, 500, 300);
        JEditorPane text = new JEditorPane();
        text.setText("e");
        JMenuBar menu = new JMenuBar();

        outline.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 1;
        c.gridwidth = 3;

        outline.add(menu, c);
        outline.add(text);
        outline.setVisible(true);
    }

}
