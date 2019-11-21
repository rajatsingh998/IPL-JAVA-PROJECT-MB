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
            ArrayList<HashMap<String, String>> data;
            ArrayList<HashMap<String, String>> data1;
            data=addd(my_file);
            data1=addd(my_file1);


            //Question 1: Number of matches played per year of all the years in IPL.
            HashMap<String, Integer> ques1= new HashMap<>();
            String date;
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

            //Question 2:  Number of matches won of all teams over all the years of IPL.
            HashMap<String, Integer> ques2= new HashMap<>();
            String team;

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

            //Question 3: For the year 2016 get the extra runs conceded per team.
            ArrayList<String> my_id= new ArrayList<>();
            String year;
            for(int i=0;i<data.size();i++){
                year=data.get(i).get("season");
                if(year.equals("2016")){
                    my_id.add(data.get(i).get("id"));
                }
            }

            HashMap<String, Integer> ques3= new HashMap<>();

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
            System.out.println("For the year 2016  the extra runs conceded per team.:"+ques3);

            //Question 4: For the year 2015 get the top economical bowlers.
            ArrayList<String> id= new ArrayList<>();
            for(int i=0;i<data.size();i++){
                year=data.get(i).get("season");
                if(year.equals("2015")){
                    id.add(data.get(i).get("id"));
                }
            }

            HashMap<String, Integer> player= new HashMap<>();
            HashMap<String, Integer> ques4= new HashMap<>();
            HashMap<String, Integer> bowl= new HashMap<>();
            for(int i=0;i<data1.size();i++){
                String val=data1.get(i).get("match_id");
                if(id.contains(val)){
                    String player_name=data1.get(i).get("bowler");
                    String total_runs= data1.get(i).get("total_runs");

                    if(player.containsKey(player_name)){
                        int run=player.get(player_name);
                        run=run+Integer.parseInt(total_runs);

                        int no_of_ball=bowl.get(player_name);
                        no_of_ball=no_of_ball+1;

                        player.put(player_name,run);
                        bowl.put(player_name, no_of_ball);
                    }
                    else{
                        int run=Integer.parseInt(total_runs);
                        player.put(player_name,run);

                        bowl.put(player_name,1);

                    }
                }
            }

            for(String keyy: player.keySet()){
                String player_name=keyy;
                int total_ball=bowl.get(player_name);
                int over=(total_ball/6);
                int total_runs= player.get(player_name);

                int eco=(total_runs/over);
                ques4.put(player_name, eco);

            }
            HashMap<String, Integer> finall=sortByValue(ques4);
            HashMap<String, Integer> ans= new HashMap<>();
            int i=1;
        for (Map.Entry<String, Integer> entry : finall.entrySet())
        {
          ans.put(entry.getKey(),entry.getValue());
          i++;
          if(i>3){
              break;
          }
        }
        System.out.print("\n For the year 2015 get the top economical bowlers."+ans);

        // Question 5:
        HashMap<String, Integer> ques5= new HashMap<>();
        String player_of_the_match;
        for(int j=0;j<data.size();j++){
            player_of_the_match=data.get(j).get("player_of_match");
            if(player_of_the_match.length()>0) {
                if (ques5.containsKey(player_of_the_match)) {
                    int val = ques5.get(player_of_the_match);
                    val = val + 1;
                    ques5.replace(player_of_the_match, val);
                } else {
                    ques5.put(player_of_the_match, 1);
                }
            }
        }

        System.out.println("\n No of player of the match :"+ ques5);

    }
 public static  ArrayList<HashMap<String, String>> addd(String my_file) {
     try {
         FileReader filereader = new FileReader(my_file);
         CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(0).build();
         List<String[]> allData = csvReader.readAll();
         System.out.println(allData.size());
         ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();


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

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> ques4)
    {

        List<Map.Entry<String, Integer> > list = new LinkedList<>(ques4.entrySet());


        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));


        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> tempp : list) {
            temp.put(tempp.getKey(), tempp.getValue());
        }
        return temp;
    }

}
