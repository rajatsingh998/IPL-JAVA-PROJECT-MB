package com.company;


import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import com.opencsv.*;
import java.io.IOException;
public class my_project
{


    public static void main(String[] args) {
        try {
            String my_file="D:\\MY\\Mount blue\\matches.csv"; //Give dataset path here
            FileReader filereader = new FileReader(my_file);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(0).build();
            List<String[]> allData = csvReader.readAll();
            System.out.println(allData.size());
            ArrayList<HashMap<String, String>> data= new ArrayList<HashMap<String, String>>();
            int x=allData.size();

            String[] keys=allData.get(0);
            for (String[] row : allData) {
                int counter=0;
                HashMap<String, String> map= new HashMap<String, String>();
                for (String cell : row) {
                    map.put(keys[counter], cell);
                    counter++;
                }
                data.add(map);

            }
            data.remove(0);

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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
