import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Model. Stores file writing, pathway, file name
 *
 * @author Henry Niermann
 *
 */
public final class Model {

    String destination = "C:\\Users\\Public\\";
    String name;
    List<String> text;
    File file;

    public Model() {
        this.name = "*Unnamed.txt";
        this.file = new File(this.destination + this.name);
        this.text = new LinkedList<>();
    }

    public Model(String destination) {
        this.file = new File(destination);
        this.destination = destination;

        int index = destination.length() - 1;
        while (this.destination.charAt(index) != '\\') {
            index--;
        }
        this.name = this.destination.substring(index + 1);

        this.text = new LinkedList<>();
        BufferedReader words;
        try {
            words = new BufferedReader(new FileReader(this.file));
            String line = words.readLine();
            while (line != null) {
                this.text.add(line);
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

    public List<String> getText() {
        return this.text;
    }

    public void setText(List<String> text) {
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

    public void writeToFile(List<String> text2) {

        while (text2.size() > 0) {
            String s = text2.remove(0);

            try {
                FileWriter write = new FileWriter(this.file);
                write.write(s);
                write.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

}
