import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Model. Stores file writing, pathway, file name
 *
 * @author Henry Niermann
 *
 */
public final class Model {

    String destination = "C:\\Users\\Public\\";
    String name;
    String text;
    File file;

    public Model() {
        this.name = "*Unnamed.txt";
        this.file = new File(this.destination + this.name);
        this.text = "";
    }

    public Model(String name1) {
        this.file = new File(name1);
        this.name = name1;
        BufferedReader words;
        try {
            words = new BufferedReader(new FileReader(this.file));
            String line = words.readLine();
            while (line != null) {
                this.text = this.text + line;
                line = words.readLine();
            }
            words.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
