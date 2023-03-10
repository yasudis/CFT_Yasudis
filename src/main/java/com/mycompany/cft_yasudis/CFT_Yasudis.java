/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cft_yasudis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;



/**
 *
 * @author Yasudis
 */
public class CFT_Yasudis {

    String[] comandsConsole;//чтение команды

    BufferedWriter resultFile;

    boolean whatIsType = true; //true-строка, false-число
    boolean whatIsSort = true;//true-возрастание, false-убывание
    boolean hasResultFile = true;

    ArrayList<BufferedReader> listFile = new ArrayList<BufferedReader>();
    ArrayList<String> listLine = new ArrayList<String>();
    int count;
    String beforResalt=" ";
    String afteResalt;

    String fileSeparator = System.getProperty("file.separator");

    private void ReadComandOnConsole(String[] args ) throws IOException {
        //Scanner in = new Scanner(System.in);
        boolean hasFirstComand = true;
        listFile = new ArrayList<BufferedReader>();
        listLine = new ArrayList<String>();
        count = 0;
        int i;//место выходного файла
        //String[] readComand = in.nextLine().split(" ");
        String[] readComand=args;
        //if (readComand==""){
        //     System.out.println("пусая строка, комадну выполнить невозможно- повторите попытку.");
        //     readComandOnConsole();
        // }
       // System.out.println(Arrays.toString(readComand));
        comandsConsole = GetComandInStringArray(readComand);
        //System.out.println(Arrays.toString(comandsConsole));
        //in.nextLine().split(" ");
        if ((CheckNullComand()) == false) {
            System.out.println("ошибка ввода команды, выполнить невозможно- выход из программы.");
            System.exit(0);
        }
        switch (comandsConsole[0]) {
            case "-a":
                whatIsSort = true;
                hasFirstComand = true;
                i = 2;
                break;
            case "-d":
                whatIsSort = false;
                hasFirstComand = true;
                i = 2;
                break;
            case "-s":
                whatIsSort = true;
                whatIsType = true;
                hasFirstComand = false;
                i = 1;
                break;
            case "-i":
                whatIsSort = true;
                whatIsType = false;
                hasFirstComand = false;
                i = 1;
                break;
            default:
                whatIsSort = true;
                hasFirstComand = true;
                i = 2;
                System.out.println("Неправильная первая команда сортировки, сортировка будет сделана по возрастанию");
                break;
        }
        if (hasFirstComand) {
            switch (comandsConsole[1]) {
                case "-s" ->
                    whatIsType = true;
                case "-i" ->
                    whatIsType = false;
                default -> {
                    whatIsType = true;
                    System.out.println("Неправильная вторая команда сортировки, сортировка будет сделана для символов");
                }
            }
        } else {
            switch (comandsConsole[1]) {
                case "-a" -> {
                    whatIsSort = true;
                    System.out.println("Неправильный порядок команд");
                    i = 2;
                }
                case "-d" -> {
                    whatIsSort = false;
                    System.out.println("Неправильный порядок команд");
                    i = 2;
                }
            }
        }

        CreateListFile(i);
        try {
            resultFile = new BufferedWriter(new FileWriter(("D:\\test\\"+comandsConsole[i])));
        } catch (IOException e) {
            //Ошибка: ошибка записи!
            resultFile = new BufferedWriter(new FileWriter(("D:\\test\\result.txt")));
            System.out.println("нету файла, создаётся новый файл result.txt");

        }
        SortLineFile();
    }

    private String[] GetComandInStringArray(String[] input) {
       Set<String> out = new LinkedHashSet();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < input.length; i++) {
            out.add(input[i]);
        }
        //System.out.println(out);
        for (var z : out) {
            result.add(z);
        }
        return result.toArray(new String[result.size()]);
    }

    private void CreateListFile(int k) throws FileNotFoundException, IOException {
        count = 0;
        for (int i = (k + 1); i < comandsConsole.length; i++) {
            try {
                listFile.add(new BufferedReader(new FileReader("D:\\test\\" +comandsConsole[i])));

                listLine.add(CheckRelevantElement(count));
            } catch (IOException ex) {
                listFile.add(null);
                listLine.add(null);
            }
            count++;
        }
        if (CheckRelevantFile() == false) {
            System.out.println("неккоректные файлы, сортировка невозможна-закрытие программы");
            System.exit(0);
        }
    }

    private boolean CheckRelevantFile() {
        boolean result = false;
        for (int i = 0; i < count; i++) {
            if (listLine.get(i) != null) {
                result = true;
            }
        }
        return result;
    }

    private boolean CheckNullComand() {
        boolean result = false;
        for (int i = 0; i < comandsConsole.length; i++) {
            if (comandsConsole[i] != null) {
                result = true;
            }
        }
        if (comandsConsole.length < 3) {
            result = false;
        }
        return result;
    }

    private void SortLineFile() throws FileNotFoundException, IOException {

       // ArrayList<String> result = new ArrayList<String>();
        System.out.println((listLine));
        int i = 0;
        while (CheckNullEltvtnts() != true) {
            int value = CompareElements();
            //result.add(listLine.get(value));
            beforResalt=listLine.get(value);
            resultFile.write((listLine.get(value)));
            resultFile.newLine();
            listLine.set(value, (CheckRelevantElement(value)));            
            //beforResalt=afteResalt;
            System.out.println((listLine));
            i++;
        }
        //System.out.println((result));
        resultFile.close();
        ClosedFile();
    }

    private void ClosedFile() throws IOException {
        for (int i = 0; i < count; i++) {
            if (listFile.get(i) != null) {
                listFile.get(i).close();
            }
        }
    }

    private int CompareElements() {

        String maxNumber = null;
        int maxIndex = 0;
        for (int k = 0; k < listLine.size(); k++) {
            if (listLine.get(k) != null) {
                maxNumber = listLine.get(k);
                maxIndex = k;
            }
        }

        for (int i = 0; i < listLine.size(); i++) {
            if (listLine.get(i) != null) {
                if (CompareElement(maxNumber, listLine.get(i))) {
                    maxNumber = listLine.get(i);
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    private boolean CompareElement(String first, String second) {

        if (whatIsSort) {
            if (ConvertElement(first) >= ConvertElement(second)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (ConvertElement(first) <= ConvertElement(second)) {
                return true;
            } else {
                return false;
            }
        }
        
    }

    private int ConvertElement(String element) {
       if (whatIsType) {
            return element.length();
        }             
        else {
            return Integer.parseInt(element);
        }   
    }

    private boolean CheckNullEltvtnts() {
        boolean result = true;
        for (int k = 0; k < listLine.size(); k++) {
            if (listLine.get(k) != null) {
                result = false;
            }
        }
        return result;
    }

    private String CheckRelevantElement(int i) throws IOException {
        String element = (listFile.get(i)).readLine();    
        boolean relevant = false;
        while (relevant != true) {
            // System.out.println(" CheckRelevantElement");            
            if (CheckRelevant(element)) {
                relevant = true;
            } else {
                element = (listFile.get(i)).readLine();          
            }
            
        }
     
        try{return element.trim();}
        catch (Exception e){
        return element;}
    }

    private boolean CheckRelevant(String element) {

        boolean result = true;

        if (element != null) {
            element=element.trim();
            if (whatIsType) {
                if (element.contains(" ")||(element.isEmpty())) {
                    System.out.println("строка имеет пробелы- элемент некорректный");
                    result = false;
                }
                else{
                    if (CompareElement(element,beforResalt)==false){
                        System.out.println("Сортировка в файле нарушина-элемент не подходит сортировке");
                        result = false;
                    }
                }
            } 
            else {
                try {
                    Integer.valueOf(element);
                    result = true;
                    if (whatIsSort){
                        if (beforResalt!=" "){
                        if (Integer.valueOf(element)>=Integer.valueOf(beforResalt)){
                            result = true;
                            }
                        else {
                            System.out.println("Сортировка в файле нарушина-элемент не подходит сортировке");
                            result=false;
                        }
                        }
                    }
                    else {
                        if (beforResalt!=" "){
                        if (Integer.valueOf(element)<=Integer.valueOf(beforResalt)){
                            result = true;
                            }
                        else {
                            System.out.println("Сортировка в файле нарушина-элемент не подходит сортировке");
                            result=false;
                        }
                        }
                    }   
                } catch (NumberFormatException e) {
                    result = false;
                    System.out.println("строка не являеться числом- элемент некорректный");
                }                            
            }       
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        CFT_Yasudis doit = new CFT_Yasudis();

        try {
            doit.ReadComandOnConsole(args);
            System.out.println("Программа завершена.");
        } catch (IOException e) {
            System.out.println("Что-то пошло не так- завершение программы");
        }
    }
}
