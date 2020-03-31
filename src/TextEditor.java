
public final class TextEditor extends View {

    private TextEditor() {

    }

    public static void main(String args[]) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, model);

        view.registerObserver(controller);
    }
}