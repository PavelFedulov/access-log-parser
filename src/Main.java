import Parsers.Extractor;
import Parsers.LineTooLongException;
import Parsers.LogEntry;
import Parsers.UserAgentExtractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;

public class Main {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        int attempts = 0;
        int total = 0;
        int yandexBotCount = 0;
        int googleBotCount = 0;
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

            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    if (length > 1024) {
                        throw new LineTooLongException("Строка длиной " + length + " превышает лимит 1024");
                    }

                    total++;

                    LogEntry entry = Extractor.extract(line);
                    String program = UserAgentExtractor.extractUserAgent(entry.getUserAgent());

                    if ("YandexBot".equals(program)) {
                        yandexBotCount++;
                    } else if ("Googlebot".equals(program)) {
                        googleBotCount++;
                    }

                }
            } catch (LineTooLongException e) {
                System.err.println(e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (total == 0) {
                System.out.println("Не найдено подходящих строк");
            } else {
                double yandexPart = yandexBotCount / (double) total;
                double googlePart = googleBotCount / (double) total;

                System.out.println("YandexBot: " + yandexBotCount + " (" + (yandexPart * 100) + "%)");
                System.out.println("Googlebot: " + googleBotCount + " (" + (googlePart * 100) + "%)");
            }
            break;
        }
    }
}
