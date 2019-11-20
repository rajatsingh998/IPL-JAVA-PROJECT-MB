package com.company;


import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.opencsv.*;
import java.io.IOException;

import static java.util.Collections.*;

public class my_project
{


    public static void main(String[] args) {

            String my_file="D:\\MY\\Mount blue\\matches.csv"; //Give dataset path here
            String my_file1="D:\\MY\\Mount blue\\deliveries.csv";
            ArrayList<HashMap<String, String>> data= new ArrayList<HashMap<String, String>>();
            ArrayList<HashMap<String, String>> data1= new ArrayList<HashMap<String, String>>();
            data=addd(my_file);
            data1=addd(my_file1);


            //Question 1:
            HashMap<String, Integer> ques1= new HashMap<String, Integer>();
            String date=new String();
            for(int i=0;i<data.size();i++){
                date=data.get(i).get("season");
                if(ques1.containsKey(date)){
                   int val=ques1.get(date);
                   val=val+1;
                    ques1.replace(date,val);
                }
                else{
                    ques1.put(date,1);
                }
            }
             System.out.println("Number of matches played per year of all the years in IPL are: \n"+ ques1);

            //Question 2:
            HashMap<String, Integer> ques2= new HashMap<String, Integer>();
            String team=new String();

            for(int i=0;i< data.size();i++){
                team=data.get(i).get("winner");
                if(team.length()>0) {
                    if (ques2.containsKey(team)) {
                        int val = ques2.get(team);
                        val = val + 1;
                        ques2.put(team, val);
                    } else {
                        ques2.put(team, 1);
                    }
                }
            }

            System.out.println("\n"+"Number of matches won of all teams over all the years of IPL."+ques2);

            //Question 3:
            ArrayList<String> my_id= new ArrayList<String>();
            String year=new String();
            for(int i=0;i<data.size();i++){
                year=data.get(i).get("season");
                if(year.equals("2016")){
                    my_id.add(data.get(i).get("id"));
                }
            }

            HashMap<String, Integer> ques3= new HashMap<String, Integer>();

            for (int i=0;i<data1.size();i++){
               String val=data1.get(i).get("match_id");
               if(my_id.contains(val)){
                    team=data1.get(i).get("bowling_team");
                   String extra_runs=data1.get(i).get("extra_runs");

                   if (ques3.containsKey(team)){
                       int run=ques3.get(team);
                       run=run+Integer.parseInt(extra_runs);
                       ques3.put(team,run);
                   }
                   else{
                       int run=Integer.parseInt(extra_runs);
                       ques3.put(team, run);
                   }
               }
           }
            System.out.println("For the year 2016 get the extra runs conceded per team.:"+ques3);

    }
 public static  ArrayList<HashMap<String, String>> addd(String my_file) {
     try {
         FileReader filereader = new FileReader(my_file);
         CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(0).build();
         List<String[]> allData = csvReader.readAll();
         System.out.println(allData.size());
         ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
         int x = allData.size();

         String[] keys = allData.get(0);
         for (String[] row : allData) {
             int counter = 0;
             HashMap<String, String> map = new HashMap<String, String>();
             for (String cell : row) {
                 map.put(keys[counter], cell);
                 counter++;
             }
             data.add(map);

         }
         data.remove(0);
         return data;
     } catch (Exception e) {
         e.printStackTrace();
     }
     ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
 return data;}
}
