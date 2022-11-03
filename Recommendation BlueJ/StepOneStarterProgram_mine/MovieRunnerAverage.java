
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        int NoMovies = sr.getMovieSize();
        int NoRaters = sr.getRaterSize();
        System.out.println("Total Movies in file : " + NoMovies);
        System.out.println("Total Raters in file : " + NoRaters);
     
        /*
         * The below prints the name of all the movies along with their 
         * average ratings in ascending order, given that the there are atleast 
         *  'x' minimum raters who rated that movie; here x = 2;
         */
        
        ArrayList<Rating> R = sr.getAverageRatings(2); //(x)
        Collections.sort(R); 
        /*
        for(int i = 0; i < R.size()-1; i++){
            for(int j = i+1; j < R.size(); j++){
                if(R.get(i).compareTo(R.get(j)) == 1){
                    Rating temp = R.get(i);
                    R.set(i, R.get(j));
                    R.set(j, temp);
                }
            }
        }
        */
        
        for(Rating k : R){
            System.out.println(k.getValue() + " : " +  sr.getTitle(k.getItem()) );
        }
        
    }
    
    
    //prints average rating of one movie
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        String t = "The Godfather";
        int flag = 0;
        
        ArrayList<Rating> R = sr.getAverageRatings(1);
        for(Rating r : R)
        {
            String s = sr.getTitle(r.getItem());
            if(s.equals(t))
            {
                flag = 1;
                System.out.println("AVG Rating of " + t + " is : " + r.getValue());
                break;
            }
        }
        /*
        for(String s : sr.map.keySet()){
            if(sr.getTitle(s).equals(t)){
                double avg = 0;
                ArrayList<Double> rating = sr.map.get(s);
                for(Double x : rating){
                    avg += x;
                }
                avg = avg/ rating.size();
                flag = 1;
                System.out.println("AVG Rating of " + t + " is : " + avg);
                
            }
        }*/
        if(flag == 0){
            System.out.println("no avg rating");
        }
    }
}
