import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        int attemps = 0;

        while (true) {
            System.out.println("Укажите путь к файлу:");

            String path = new Scanner(System.in).nextLine();
            File file = new File(path);

            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists || isDirectory) {
                System.out.println("Указанный файл не существует или указанный путь является путем к папке, а не файлу.");
                continue;
            }

            attemps++;
            System.out.println("Путь указан верно.");
            System.out.println("Это файл номер: " + attemps);
        }
    }
}
