package mycode;

/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    public HashMap<String, ArrayList<Double>> map;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
        build();
    }
    
    
    //Constructor to add movies and ratings to ArrayList
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
        build();
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    
    //HashMap mapping [ MovieID -> No of Ratings it recieved and theier values ]
    private void build(){
        map = new HashMap<String, ArrayList<Double>> ();
        for(Rater r : myRaters){
            ArrayList<String> items = r.getItemsRated();
            for(String s : items){
                if(map.containsKey(s)){
                    ArrayList<Double> ratings = map.get(s);
                    Double temp = r.getRating(s);
                    ratings.add(temp);
                    map.put(s, ratings);
                }
                else{
                    ArrayList<Double> Nratings = new ArrayList<Double>();
                    Double temp = r.getRating(s);
                    Nratings.add(temp);
                    map.put(s, Nratings);
                }
            }
        }
        //map created with key as movieID and value as array of ratings.
    }
    
    
    //id = movie id
    private double getAverageByID(String id, int minimalRaters){
        //
        ArrayList<Double> ratings = map.get(id);
        int Rsize = 0;
        try{
            Rsize = ratings.size();
        }
        catch(Exception e){
            Rsize = 0;
        }
        if(Rsize >= minimalRaters){
            double avg = 0.0;
            for(Double x : ratings){
                avg += (double)x;
            }
            avg = avg / Rsize;
            return avg;
        }
        
        return 0.0;
       
    }
    
    
    //
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        //Rating (String anItem, double aValue)
        ArrayList<Rating> R = new ArrayList<Rating>();
        for(Movie m : myMovies){
            double avgrating = getAverageByID(m.getID(), minimalRaters);
            if(avgrating != 0.0){
                Rating temp = new Rating(m.getID(), avgrating);
                R.add(temp);
            }
        }
        return R;
    }
    
    
    //here i may have to use % like operator to obviate alphabet cases
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "ID not found";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "Title not found";
    }
}