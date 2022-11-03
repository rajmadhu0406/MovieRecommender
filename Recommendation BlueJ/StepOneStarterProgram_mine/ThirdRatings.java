
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class ThirdRatings {
        //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    public HashMap<String, ArrayList<Double>> map;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        //myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
        //build();
    }
    
    //here is how we can  get our next rater id
    public int getRaterSize(){
        return myRaters.size();
    }
    
    /*
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
    
    */
    //
    private double getAverageByID(String id, int minimalRaters){
        //
        //ArrayList<Double> ratings = map.get(id);
        
        double count = 0;
        double score = 0.0;
        for(Rater r : myRaters)
        {
            if(r.hasRating(id) )
            {
                count++;
                score += r.getRating(id);
            }
        }
        if(count >= minimalRaters)
        {
            return (score/count);
        }
        /*
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
        */
        return 0.0;
       
    } 
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        //Rating (String anItem, double aValue)
        ArrayList<Rating> R = new ArrayList<Rating>();
         //ArrayList<String> moviesnames = getItemsRated()
         Filter f = new TrueFilter();
         ArrayList<String> Moviesnames = MovieDatabase.filterBy(f);

            for(String m : Moviesnames){
            double avgrating = getAverageByID(m, minimalRaters);
            if(avgrating != 0.0){
                Rating temp = new Rating(m, avgrating);
                R.add(temp);
            }
          }
           return R;
        }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        // Filter f = new YearAfterFilter(2000);
        //returns ArrayList<String> of movieIDs
        ArrayList<String> filteredNames = MovieDatabase.filterBy(filterCriteria);
        
        ArrayList<Rating> R = new ArrayList<Rating>();

            for(String m : filteredNames){
            double avgrating = getAverageByID(m, minimalRaters);
            if(avgrating != 0.0){
                Rating temp = new Rating(m, avgrating);
                R.add(temp);
            }
          }
           return R;
      
    }
    }
    
