package ru.inno;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 23.09.2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        String str = "";
        ThreadX t;
        ArrayList<ThreadX> threads = new ArrayList<ThreadX>();

// Запуск потокв
        int x = 0;
        for (String arg : args) {
            x++;
            str = "Запуск потока-:"+x+"; ("+arg+")";
            System.out.println(str);
            t = new ThreadX(arg);
            t.setName(str);
            threads.add(t);
            t.start();
        }

// Завершение работы потоков
        for (ThreadX th:threads){
            if (th.isAlive())
                try {
                    th.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }

// Вывод результатов
//        System.out.println("\nВывод результатов\n==================");
//        System.out.println("Всего строк:\t" + ReadFile.kolStrok);
//        System.out.println("Всего  слов:\t" + ReadFile.kolWord);
//        System.out.println("Статистика:");
//
//        Set set = ReadFile.treeMap.entrySet();
//        int si=0;
//        for (Object element : set) {
//            Map.Entry mapEntry = (Map.Entry) element;
//            System.out.println(++si+".\t " + mapEntry.getKey()+": \t"+mapEntry.getValue());
//        }

//        File file = new File("Statistic.txt");
    if (ReadFile.sem == 0) {
        try(FileWriter writer = new FileWriter("Statistic.txt", false)) {
            writer.write("\nВывод результатов\n==================");
            writer.write("\nВсего строк:\t" + ReadFile.kolStrok);
            writer.write("\nВсего  слов:\t" + ReadFile.kolWord);
            writer.write("\nСтатистика:");

            Set set = ReadFile.treeMap.entrySet();
            int si = 0;
            for (Object element : set) {
                Map.Entry mapEntry = (Map.Entry) element;
                writer.write("\n" + (++si) + ". \t<" + mapEntry.getKey() + "> - \t" + mapEntry.getValue());
            }

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    } else
        {
            System.out.println("\nПотоки остановлены, в тексте есть латиница!");
        }

     System.out.println("\nКонец работы!!!");
    }

}