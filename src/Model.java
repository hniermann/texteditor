import java.io.File;

/**
 * Put a short phrase describing the program here.
 *
 * @author Henry Niermann
 *
 */
public final class Model {

    String destination;
    String name;
    File file;

    public Model() {
        this.file = new File("Unnamed.txt");
        this.destination = "C:\\Users\\henry\\";
        this.name = "Unnamed";
    }

    public Model(String name1) {
        this.file = new File(name1 + ".txt");
        this.destination = "C:\\Users\\henry\\";
        this.name = name1;
    }

    public File getFile() {
        return this.file;
    }

    public void setFileName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return this.name;
    }

}
