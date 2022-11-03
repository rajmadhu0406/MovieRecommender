
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class MinutesFilter implements Filter{
    int min;
    int max;
    public MinutesFilter(int MIN, int MAX){
        min = MIN; 
        max = MAX;
    }
    
    @Override
    public boolean satisfies(String id) {
        return  MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
