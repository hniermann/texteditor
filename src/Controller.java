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
        this.model.setText(this.view.getText().getText());
        this.model.saveFile();
        System.out.println(this.model.getName());
        this.view.changeDocName(this.model.getName());
    }

}
