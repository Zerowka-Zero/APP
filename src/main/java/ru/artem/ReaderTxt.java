package ru.artem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReaderTxt {

    public static final Logger log = Logger.getLogger(ReaderTxt.class.getName());

    public static LinkedList<Question> formatter() {
        LinkedHashSet<Question> list = new LinkedHashSet<>();
        list.add(new Question("Начало", new LinkedList<>(List.of("", "", "", "")), 0));
        String key_value = "";
        LinkedList<String> temp_list = new LinkedList<>();

        try (BufferedReader fr = new BufferedReader(new FileReader("NO_FORMAT.txt"))) {
            String s = fr.readLine();
            int count = 1, answer = 0;

            while (s != null) {
                if (s.contains("\t") || s.isBlank()) {
                    s = fr.readLine();
                    continue;
                }

                if (count == 1) {
                    key_value = s;
                } else if (count <= 4) {
                    temp_list.add(s);
                    answer = s.contains("~") ? count - 1 : answer;
                } else {
                    temp_list.add(s);
                    answer = s.contains("~") ? count - 1: answer;
                    temp_list.replaceAll(str -> str.replace("~", ""));
                    list.add(new Question(key_value,temp_list, answer));
                    count = 1;
                    temp_list = new LinkedList<>();
                    s = fr.readLine();
                    continue;
                }
                count++;
                s = fr.readLine();
            }


        } catch (IOException ex) {
            log.log(Level.WARNING, ex.getMessage());
        }
        list.add(new Question("Конец", new LinkedList<>(List.of("", "", "", "")), 0));
        return new LinkedList<>(list);
    }
}
