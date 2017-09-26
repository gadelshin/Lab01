package ru.inno;

import java.io.IOException;

/**
 * Created by admin on 23.09.2017.
 */
public class ThreadX extends Thread {
    protected String fileName;

    public ThreadX(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void run() {
        if (ReadFile.sem==1){
            System.out.println("Останов, файл "+fileName);
            //this.interrupt();
            return;
        }
            try {
//                System.out.println(getName() + ":\t читает файл: "+fileName);
                ReadFile text = new ReadFile(fileName);
                text.readFile();
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
    }

}
