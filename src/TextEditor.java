import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class TextEditor extends View {

    private TextEditor() {

    }

    public static void main(String args[]) {

        // Set System L&F
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, model);

        view.registerObserver(controller);

    }
}