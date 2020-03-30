/**
 *
 *
 * @author Henry Niermann
 *
 */
public class Controller {

    private View view;

    private Model model;

    public Controller(View view1, Model model1) {
        this.view = view1;
        this.model = model1;
    }

    public void saveEvent() {
        String lines = this.view.text.getText();
        this.model.writeToFile(lines);
    }

}
