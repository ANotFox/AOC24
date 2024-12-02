import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class template {
    static String filename = "data/d.txt";

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Hello, World!");
    }
}