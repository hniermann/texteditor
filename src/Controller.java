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
    }

    public void saveAsWindowEvent() {
        this.view.queueName();
    }

    public void saveAsConfirmedEvent() {
        this.model.setText(this.view.getText().getText());
        this.model.setName(this.view.getNewName());
        this.model.setPath(this.view.getNewPath());
        this.model.saveFile();
        this.view.changeDocName(this.model.getName());
    }

    public void unsavedTextEvent() {
        this.view.changeDocName("*" + this.model.getName());
    }

}
