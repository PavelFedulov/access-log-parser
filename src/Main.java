import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Укажите путь к файлу:");

            String path = scanner.nextLine();
            File file = new File(path);

            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists || isDirectory) {
                System.out.println("Указанный файл не существует или указанный путь является путем к папке, а не файлу.");
                continue;
            }

            attempts++;

            System.out.println("Путь указан верно.");
            System.out.println("Это файл номер: " + attempts);

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                long totalLines = 0;
                int maxLen = 0;
                int minLen = Integer.MAX_VALUE;

                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) {
                        throw new LineTooLongException("Строка длиной " + length + " превышает лимит 1024");
                    }
                    totalLines++;
                    maxLen = Math.max(maxLen, length);
                    minLen = Math.min(minLen, length);
                }

                if (totalLines == 0) {
                    minLen = 0;
                    maxLen = 0;
                }

                System.out.println("Общее количество строк в файле: " + totalLines);
                System.out.println("Длина самой короткой строки: " + minLen);
                System.out.println("Длина самой длинной строки: " + maxLen);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            break;
        }
    }
}
