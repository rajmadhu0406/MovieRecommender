
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FourthRatings {
        //private ArrayList<Movie> myMovies;
    //private ArrayList<Rater> myRaters;
    public HashMap<String, ArrayList<Double>> map;
    
    public FourthRatings(String file) {
        // default constructor
        RaterDatabase.initialize(file);
        if(map == null)
            build();
    }
    
    public FourthRatings(){
        RaterDatabase.initialize("ratings.csv");
        if(map == null)
            build();
    }

    /*
    public int getRaterSize(){
        return myRaters.size();
    }
    */
    private void build(){
        //movieID - Ratings in double
        map = new HashMap<String, ArrayList<Double>> ();
        
        for(Rater r : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        double ans = 0;
        ArrayList<String> movies = me.getItemsRated();
        for(String m : movies){
            if(r.hasRating(m)){
                double x1 = me.getRating(m);
                double x2 = r.getRating(m);
                x1 = x1 - 5;
                x2 = x2 - 5;
                double temp = x1*x2;
                ans += temp;
                
            }
        }
       // System.out.println("zzzzzzz====" + ans);
        return ans;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        //here Rating object contains id = Rater ID and value = dotProduct();
        //RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> ans = new ArrayList<Rating>();
        Rater me =  RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            String RID = r.getID();
            
            
            if(! id.equals(RID)){
                //System.out.println("R in ID : " + RID);
                double x = dotProduct(me, r);
                if(x >= 0){
                    Rating temp = new Rating(RID, x);
                    ans.add(temp);
                }
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        //System.out.println(ans);
        return ans;
    }
    
    //raterID, limit, minRaters
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        //here in ans, id = movie ID and value = weighted average rating 
        ArrayList<Rating> ans = new ArrayList<Rating>();
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> similar = getSimilarities(id);
        
        Rater me = RaterDatabase.getRater(id);
        
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter()); 
        for(String m : movies){//movie ids iterate
            int count = 0;
            double rate = 0;
            for(int i = 0; i < numSimilarRaters; i++){
                /////////////////////////////////////////////////////////////////////
                String RID = similar.get(i).getItem();//rater id
                //System.out.println("RID : " + RID);
                //r = similar rater
                Rater r = RaterDatabase.getRater(RID);
                double x = -1;
                if(r.hasRating(m) && !me.hasRating(m)){
                     x = r.getRating(m);
                }
                if(x != -1){
                    count++;
                    rate += (x * similar.get(i).getValue());
                }
            }
            if(count >= minimalRaters){
                rate = (double)rate / count;  //avg rating
                Rating temp = new Rating(m, rate);
                ans.add(temp);
            }
        }
        Collections.sort(ans, Collections.reverseOrder());
        return ans;
    }
    
    public  ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
         ArrayList<Rating> ans =   getSimilarRatings(id, numSimilarRaters, minimalRaters);
         ArrayList<Rating> NEW = new ArrayList<Rating>();
         
         for(Rating r : ans){
             String MID = r.getItem();
             if(filterCriteria.satisfies(MID)){
                 NEW.add(r);
             }
         }
         Collections.sort(NEW, Collections.reverseOrder());
         return NEW;
    }
}
    
