package MainPackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Main2 {

    public static String readHtmlFromFile(String filePath) throws IOException { // Открваю файл и считываю данные
        StringBuilder htmlContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line);
            }
        }
        return htmlContent.toString();
    }

    public static String extractTextFromHtml(String html) {
        StringBuilder plainText = new StringBuilder();
        boolean insideTag = false; // Флаг, указывающий на пребывание внутри тегов

        for (int i = 0; i < html.length(); i++) { // Если внутри тега - скипаю текс, иначе добавляю в свою строку-накопитель
            char currentChar = html.charAt(i);

            if (currentChar == '<') {
                insideTag = true;
            } else if (currentChar == '>') {
                insideTag = false;
            } else if (!insideTag) {
                plainText.append(currentChar);
            }
        }

        return plainText.toString();
    }


    public static void main(String[] args) {
        try {
            // Относительный путь к файлу в той же папке
            String html = readHtmlFromFile("src/MainPackage/index.html");

            String text = extractTextFromHtml(html).trim().replaceAll("\\p{Punct}", "")
                    .replaceAll("\\s+", " ");; // Удаляю лишние пробелы, которые остались от тега <pre>


            // TreeSet - данная коллекция не содержит дубликатов и сортирует элементы в алфавитном пордке
            // Класс Colator для создания руссой локали и корректной сортирвки слов написанных кирилицей
            Set<String> wordSet = new TreeSet<>(Collator.getInstance(new Locale("ru", "RU"))) ;


            // Добавляю в TreeSet массив из слов, на которые разбиваю строку c текстом
            Collections.addAll(wordSet, text.split(" "));

            System.out.println(wordSet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
