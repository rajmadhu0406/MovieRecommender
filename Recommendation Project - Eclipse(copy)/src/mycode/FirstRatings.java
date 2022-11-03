package mycode;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> movie = new ArrayList<Movie>();
        
        FileResource f = new FileResource("data/"+filename);
        CSVParser parser = f.getCSVParser();
        
        for(CSVRecord re : parser)
        {
            Movie obj = new Movie(re.get(0), re.get(1), re.get(2), re.get(4), re.get(5), re.get(3), re.get(7),re.get(6));
            movie.add(obj);                     
        }
        
        return movie;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> am = loadMovies("ratedmovies_short.csv");
        System.out.println("No of movies : " + am.size());
        //print movies in csv file
        /*
        for(Movie m : am){
            System.out.println(m);
        }
        */
       
        //printing movie of comedy genre and time>150
        int count = 0, min = 0;
        for(Movie m : am){
            if(m.getGenres().indexOf("Comedy") != -1){
                count++;
            }
            if(m.getMinutes() > 150){
                min++;
            }
            
        }
        System.out.println("Comedy : " + count + "\nMinutes > 150 : " + min);
        
        //counting directors and no. of films they directed
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(Movie m : am){
            String d = m.getDirector();
            String[] arr = d.split(",");
            for(int i = 0; i < arr.length; i++){
                String s = arr[i].trim();
                if(map.containsKey(s)){
                    map.put(s, map.get(s) + 1);
                }
                else{
                    map.put(s, 1);
                }
            }
        }
        
        //max directions
        
        int max = 0;
        for(int x : map.values()){
            if(x > max){
                max = x;
            }
        }
        System.out.println("Max diretions : " + max + "\n");
        
        for(String dir : map.keySet()){
            if(map.get(dir).equals(max)){
                System.out.println(dir);
            }
        }
        
    }
    
    //...
    
        public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> R = new ArrayList<Rater>();
        
        //rater_id  movie_id    rating  time
        //Rater(String id)
        // Rating (String anItem, double aValue)
        
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord re : parser){
            Rater rat = new EfficientRater(re.get(0));
            int flag = 0;
            for(Rater r : R){
                if(r.getID().equals(rat.getID())){
                  r.addRating(re.get(1), Double.parseDouble(re.get(2)) );  
                  flag = 1;
                  break;
                }
            }
            if(flag == 0){
                rat.addRating( re.get(1), Double.parseDouble(re.get(2)) );
                R.add(rat);
            }
        }
        return R;
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> R = loadRaters("ratings_short.csv");
        System.out.println(R.size());
        /*
        for(Rater r : R){
            System.out.println("Rater Id : " + r.getID());
            System.out.println("Ratings Done : " + r.numRatings());
            ArrayList<String> items = r.getItemsRated();
            for(String s : items){
                System.out.println(s + " : " + r.getRating(s));
            }
        }
        */
        /*
         * to check no of rating of a rater in the arraylist when we ran loadRaters();
         */
        
        String CheckRater = "2";
        int tt = 0;
        for(Rater r : R){
            if(r.getID().equals(CheckRater) ){
                System.out.println("Rater Found with No. of  ratings : " + r.numRatings());
                tt = 1;
            }
        }
        if(tt == 0){
            System.out.println("Rater not found!");
        }
        
        /*
         * max of rating by a rater in arraylist R; 
         */
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Integer max = 0;
        for(Rater r : R){
            Integer x = r.numRatings();
            if(x > max){
                max = x;
            }
            map.put(r.getID(), x);
        }
        System.out.println("max ratings by rater : " + max + "\nnames of those raters are : ");
        for(String ss : map.keySet()){
            if(map.get(ss).equals(max)){
                System.out.println(ss);
            }
        }
        System.out.println();
        
        /*
         * find the number of ratings a particular movie has
         */
        
        String movie = "1798709";
        int count = 0;
         for(Rater r : R){
            ArrayList<String> items = r.getItemsRated();
            for(String s : items){
                //System.out.println(s + " : " + r.getRating(s));
                if(s.equals(movie)){
                    count++;
                }
            }
        }
        System.out.println("moiveID : " + movie + " was rated by " + count + " raters.");
       
        
        ArrayList<String> imdb = new ArrayList<String>();
        int mcount = 0;
        for(Rater r : R){
            ArrayList<String> items = r.getItemsRated();
            for(String s : items){
                if(!imdb.contains(s)){
                    imdb.add(s);
                    mcount++;
                }
            }
        }
        System.out.println("Total movies rated by all raters : " + mcount);
        
    }   
}
