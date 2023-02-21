/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cft_yasudis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author jasudis
 */
public class CFT_Yasudis {
    String a="notes3.txt";
    String b="notes4.txt";
    String[]comandsConsole;
    BufferedWriter resultFile;
    String[] countFile=new String[]{"notes3.txt","notes4.txt","notes5.txt"};
    int count;
    boolean whatIsType=true; //true-строка, false-число
    boolean whatIsSort=true;//true-возрастание, false-убывание
    boolean hasReaultFile=true;
    ArrayList<BufferedReader> listFile=new ArrayList<BufferedReader>();
    ArrayList<String> listLine=new ArrayList<String>();
    
    String fileSeparator = System.getProperty("file.separator");
    
    private void readComandOnConsole() throws IOException{
     Scanner in=new Scanner(System.in);
     boolean hasFirstComand=true;
     int i;//место выходного файла
        comandsConsole=in.nextLine().split(" ");
        switch (comandsConsole[0]) {
            case "-a":
                whatIsSort=true;
                hasFirstComand=true;
                i=2;
                break;
            case "-d":
                whatIsSort=false;
                hasFirstComand=true;
                i=2;
                break;
            case "-s":
                whatIsSort=true;
                whatIsType=true;
                hasFirstComand=false;
                i=1;
                break;
            case "-i":
                whatIsSort=true;
                whatIsType=false;
                hasFirstComand=false;
                i=1;
                break;
            default:
                whatIsSort=true;
                hasFirstComand=false;
                i=1;
                System.out.println("Неправильная первая команда сортировки, сортировка будет настроена по возрастанию");
                break;
        }
        if (hasFirstComand){
            switch(comandsConsole[1]){
            case "-s" -> whatIsType=true;
            case "-i" -> whatIsType=false;
            default -> {
                whatIsType=true;
                System.out.println("Неправильная вторая команда сортировки, сортировка будет настроена для символов");
             }                
            }
        }
       
       createListFile(i);
        try{ resultFile=new BufferedWriter(new FileWriter((comandsConsole[i])));
       }
       catch (IOException e) {
            //Ошибка: ошибка записи!
            resultFile=new BufferedWriter(new FileWriter(("result.txt")));
            System.out.println("нету файла, создаётся новый файл result.txt");
            //throw new IOException("Error: file write error!");
        }
    }
    
       // if (resultFile.exists()!=true){
       //     resultFile=new File("result.txt");
        //    resultFile.createNewFile();
       //     System.out.println("нету файла, создаётся новый файл result.txt");
         
        
    
        
        
        
    
    private void createListFile(int k) throws FileNotFoundException, IOException{
        count=0;
        for (int i=(k+1);i<comandsConsole.length;i++){
     
    try{ listFile.add(new BufferedReader(new FileReader(comandsConsole[i])));
    
        listLine.add((listFile.get(count)).readLine());
        
        }
        catch(IOException ex){
        listFile.add(null);
        listLine.add(null);
                   }
            count++;
    }
  
    }
    private void sortLineFile() throws FileNotFoundException, IOException{
       
        ArrayList<String> result=new ArrayList<String>();
                System.out.println((listLine));
        int i=0;
        while (checkNullEltvtnts()!=true) {
            int value=CompareTwoElements ();
            result.add(listLine.get(value));
            resultFile.write((listLine.get(value)));
            resultFile.newLine();
            listLine.set(value,(listFile.get(value)).readLine());
            System.out.println((listLine));
            i++;
        }
         System.out.println((result));
         resultFile.close();
         ClosedFile();         
         
   }
    private void ClosedFile() throws IOException{
        for (int i=0;i<count;i++){
        listFile.get(i).close();
        }
        
    }
    private int CompareTwoElements (){
        whatIsType=true;
        String maxNumber=null;
        int maxIndex=0;
        for (int k=0;k<listLine.size();k++){
             if (listLine.get(k)!=null){
                 maxNumber=listLine.get(k);
                 maxIndex=k;
             }
         }
        if (whatIsType){
            for (int i=0;i<listLine.size();i++){
            if (listLine.get(i)!=null)
            if(maxNumber.length() >= listLine.get(i).length()){
                maxNumber = listLine.get(i);
                maxIndex = i;
                }
        }           
        }
        return maxIndex;
       // else {
        //   if(Integer.parseInt(firstElement) < Integer.parseInt(secondElement))
        //   {}
        //    else 
         //  {}
       // }
    } 
    private boolean checkNullEltvtnts(){
        boolean result=true;
        for (int k=0;k<listLine.size();k++){
             if (listLine.get(k)!=null){
               result=false; 
             }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
     CFT_Yasudis doit=new CFT_Yasudis();
     
     doit.readComandOnConsole();
     doit.sortLineFile();
     
    }
}
