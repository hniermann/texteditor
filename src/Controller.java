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
        if (this.model.getName().contains("*")) {
            this.view.changeDocName(this.model.getName().substring(1));
            this.model.setName(this.model.getName().substring(1));
        }
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
        if (!this.model.getName().contains("*")) {
            this.view.changeDocName("*" + this.model.getName());
            this.model.setName("*" + this.model.getName());
        }
    }

    public void openButtonEvent(String path) {
        this.model = new Model(path);
        this.view.writeText(this.model.getText());
        this.view.changeDocName(this.model.getName());
    }

}
