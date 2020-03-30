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
        this.file = new File(this.destination + "Unnamed.txt");
        this.name = "Unnamed";
        this.text = "";
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.print(e);
        }
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
