
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class GenreFilter implements Filter{
    String[] genres;
    
    public GenreFilter(String genre){
        genres = genre.split(","); 
    }
    
    @Override
    public boolean satisfies(String id) {
        int flag = 0;
        String s = MovieDatabase.getGenres(id);
        for(String g : genres){
            if(s.indexOf(g) == -1){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            return true;
        }
        return false;
    }
}
