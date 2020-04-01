import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Put a short phrase describing the program here.
 *
 * @author Henry Niermann
 *
 */
public final class Model {

    String destination = "C:\\Users\\henry\\";
    String name;
    String text;
    File file;

    public Model() {
        this.name = "Unnamed.txt";
        this.file = new File(this.destination + this.name);
        this.text = "";
    }

    public Model(String name1) {
        this.file = new File(name1 + ".txt");
        this.destination = "C:\\Users\\henry\\";
        this.name = name1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPath(String path) {
        this.destination = path;
    }

    public void saveFile() {

        this.file = new File(this.destination + this.name);
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.print(e);
        }

        this.writeToFile(this.text);
    }

    public void writeToFile(String text) {
        Scanner input = new Scanner(text);
        while (input.hasNext()) {
            String s = input.nextLine();

            try {
                FileWriter write = new FileWriter(this.file);
                write.write(s);
                write.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        input.close();
    }

}
