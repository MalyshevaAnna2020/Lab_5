package Lab_5;
import java.io.*;
import java.util.*;
/**
 * The main class of an application that implements interactive object collection management
 * @author Ann
 */
public class MainConsole {
    /**
     * The main method of the class
     * @param args is empty
     * @throws IOException work with files (writing and reading)
     */
    public static void main(String[] args) throws IOException {
        Hashtable<String, SpaceMarine> hashtable = new Hashtable<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя файла для чтения:");
        try {
            File file = new File(in.nextLine());
            new CheckFile().checkFileRead(file);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
            Console c = new Console();
            hashtable = c.console(isr);
            isr.close();
            for (String keytab : hashtable.keySet()) {
                System.out.println(keytab + ": " + hashtable.get(keytab));
            }
        } catch (FileNotFoundException e){
            System.err.println("Файл для чтения не найден!");
        } catch (FileNotRead e) {
            System.err.println("Файл не доступен для чтения!");
        }
        Play p = new Play();
        System.out.println("Можно начинать вводить команды!");
        String newcommand;
        while (true){
            newcommand = in.nextLine();
            if (newcommand.length() > 256){
                System.err.println("Очень длинная строка! Введите более короткий вариант!");
                continue;
            }
            newcommand = newcommand.trim();
            if (newcommand.equals("exit")) break;
            hashtable = p.play(hashtable, newcommand);
        }

    }
}
