package ru.inno;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.awt.SystemColor.text;

public class ReadFile {
    final String SSEP = "[, !-:…]";
    protected String fileName;
    protected static int kolStrok = 0;
    protected static int kolWord  = 0;
    protected static int sem = 0;  // семафор остановки потоков volatile
    static TreeMap treeMap = new TreeMap<>();
    Pattern pLat = Pattern.compile("[a-zA-z]+");
    Pattern pSep = Pattern.compile(SSEP);

    public static void setSem(int sem) {
        ReadFile.sem = sem;
    }

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public void readFile() throws IOException {
        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> analyzeLine(s));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

//======================================================
    synchronized public void analyzeLine(String line) {//lowwer
//check stop here
// проверка на латиницу:
        Matcher m = pLat.matcher(line);
        if(m.find()) {
            setSem(1);
            System.out.println("\nПоймался! Найдена латиница: <"+line.substring(m.start(), m.end())+">");
        }
// разобрать на слова и посчитать статистику
        line = line.replace('.', ',');
        int x;
        for (String word : line.split(SSEP)) {
            Matcher mSep = pSep.matcher(word);
            if(word.length()>0 && !mSep.find() ) {
                if (treeMap.containsKey(word)) {
                    x = (int) treeMap.get(word);
                    treeMap.replace(word, x, ++x);
                } else
                    treeMap.put(word, 1);
            }
            kolWord++;
        }
        kolStrok++;
    }

}
