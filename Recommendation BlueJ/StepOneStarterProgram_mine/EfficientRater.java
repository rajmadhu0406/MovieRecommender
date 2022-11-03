
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class EfficientRater implements Rater{
    private String myID;
    public HashMap<String,Rating> map; //key = MovieID value = Rating of movie

    public EfficientRater(String id) {
        myID = id;
        map = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        Rating temp = new Rating(item,rating);
        map.put(item, temp);
    }

    public boolean hasRating(String item) {
        if(map.containsKey(item)){
            return true;
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        Rating ans = map.get(item);
        return ans.getValue();
        //return -1;
    }

    public int numRatings() {
        return map.size();
    }
    

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : map.keySet()){
            list.add(s);
        }
        
        return list;
    }
}
