package mycode;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    String[] Direct;
    
    public DirectorsFilter(String dic){
        Direct = dic.split(","); 
    }
    
    @Override
    public boolean satisfies(String id) {
        int flag = 0;
        String s = MovieDatabase.getDirector(id);
        for(String d : Direct){
            if(s.indexOf(d) != -1){
                return true;
            }
        }
        return false;
    }
}
