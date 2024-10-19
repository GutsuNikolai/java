package MainPackage;

import org.jsoup.Jsoup;
import java.text.Collator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Locale;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String filePath = "src/MainPackage/index.html";
        String html = "";

        // Открываю файл и считываю данные из него в строку
        try {
            html = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return; // Завершение программы при ошибке
        }

        // Преобразование HTML в чистый текст
        // С помощью relaceAll убираю все знаки пункутуации & спец. символы & пробелы.
        String text = Jsoup.parse(html).text().trim()
                .replaceAll("\\p{Punct}", "")
                .replaceAll("\\s+", " ");


        // TreeSet - данная коллекция не содержит дубликатов и сортирует элементы в алфавитном пордке
        // Класс Colator для создания руссой локали и корректной сортирвки слов написанных кирилицей
        Set<String> wordSet = new TreeSet<>(Collator.getInstance(new Locale("ru", "RU"))) ;


        // Добавляю в TreeSet массив из слов, на которые разбиваю строку c текстом
        Collections.addAll(wordSet, text.split(" "));

        System.out.println(wordSet);

    }
}

