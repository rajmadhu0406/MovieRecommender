
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class MovieRunnerWithFilters {
    
     public void printAverageRatings(){
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        int NoRaters = sr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        ArrayList<Rating> R = sr.getAverageRatings(1);
        System.out.println("Movies found after getAverageRating(minRaters) : " + R.size());
        
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
            System.out.println(k.getValue() + " : " + MovieDatabase.getTitle(k.getItem()) );
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        int NoRaters = tr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> frating = tr.getAverageRatingsByFilter(1, f);
        
        System.out.println("movies found : " + frating.size());
        
        Collections.sort(frating);
        
        /*
        for(int i = 0; i < frating.size()-1; i++){
            for(int j = i+1; j < frating.size(); j++){
                if(frating.get(i).compareTo(frating.get(j)) == 1){
                    Rating temp = frating.get(i);
                    frating.set(i, frating.get(j));
                    frating.set(j, temp);
                }
            }
        }
        */
        for(Rating k : frating){
            System.out.println(k.getValue() + " : " + MovieDatabase.getYear(k.getItem()) + " : " + MovieDatabase.getTitle(k.getItem()) );
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        int NoRaters = tr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f = new GenreFilter("Crime");
        //edit here below 
        ArrayList<Rating> frating = tr.getAverageRatingsByFilter(1, f);
        
        System.out.println("movies found : " + frating.size());
        
        Collections.sort(frating);
        
        /*
        
        for(int i = 0; i < frating.size()-1; i++){
            for(int j = i+1; j < frating.size(); j++){
                if(frating.get(i).compareTo(frating.get(j)) == 1){
                    Rating temp = frating.get(i);
                    frating.set(i, frating.get(j));
                    frating.set(j, temp);
                }
            }
        }*/
        
        for(Rating k : frating){
            System.out.println(k.getValue() + " : " + MovieDatabase.getTitle(k.getItem()) );
            System.out.println("\t" + MovieDatabase.getGenres(k.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        int NoRaters = tr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f = new MinutesFilter(110, 170);
        //edit here below 
        ArrayList<Rating> frating = tr.getAverageRatingsByFilter(1, f);
        
        System.out.println("\n movies found :  " + frating.size());
        
        Collections.sort(frating);
        
        /*
        for(int i = 0; i < frating.size()-1; i++){
            for(int j = i+1; j < frating.size(); j++){
                if(frating.get(i).compareTo(frating.get(j)) == 1){
                    Rating temp = frating.get(i);
                    frating.set(i, frating.get(j));
                    frating.set(j, temp);
                }
            }
        }*/
        
        for(Rating k : frating){
            System.out.println(k.getValue() + " : Time : " + MovieDatabase.getMinutes(k.getItem()) + "  " + MovieDatabase.getTitle(k.getItem()) );
            
        }
    }
    
    public void printAverageRatingsByDirector(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        int NoRaters = tr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        //edit here below 
        ArrayList<Rating> frating = tr.getAverageRatingsByFilter(1, f);
        
        System.out.println("movies found : " + frating.size());
        
        Collections.sort(frating);
        
        /*
        for(int i = 0; i < frating.size()-1; i++){
            for(int j = i+1; j < frating.size(); j++){
                if(frating.get(i).compareTo(frating.get(j)) == 1){
                    Rating temp = frating.get(i);
                    frating.set(i, frating.get(j));
                    frating.set(j, temp);
                }
            }
        }*/
        
        for(Rating k : frating){
            System.out.println(k.getValue() + "  " + MovieDatabase.getTitle(k.getItem()) );
            System.out.println("\t" + MovieDatabase.getDirector(k.getItem()));
            
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters F = new AllFilters();
        F.addFilter( new GenreFilter("Romance"));
        F.addFilter( new YearAfterFilter(1980));
        
        
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        int NoRaters = tr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        ArrayList<Rating> frating = tr.getAverageRatingsByFilter(1, F);
        
        System.out.println("movies found : " + frating.size());
        
        Collections.sort(frating);
        
        /*
        for(int i = 0; i < frating.size()-1; i++){
            for(int j = i+1; j < frating.size(); j++){
                if(frating.get(i).compareTo(frating.get(j)) == 1){
                    Rating temp = frating.get(i);
                    frating.set(i, frating.get(j));
                    frating.set(j, temp);
                }
            }
        }*/
        
        for(Rating k : frating){
            System.out.println(k.getValue() + " : " + MovieDatabase.getYear(k.getItem()) +  MovieDatabase.getTitle(k.getItem()) );
            System.out.println("\t" + MovieDatabase.getGenres(k.getItem()));
            
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes (){
        AllFilters F = new AllFilters();
        F.addFilter( new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        F.addFilter( new MinutesFilter(30, 170));
        
        
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        int NoRaters = tr.getRaterSize();
        System.out.println("Total Raters in file : " + NoRaters);
        
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        ArrayList<Rating> frating = tr.getAverageRatingsByFilter(1, F);
        
        System.out.println("movies found : " + frating.size());
        Collections.sort(frating);
        
        /*
        for(int i = 0; i < frating.size()-1; i++){
            for(int j = i+1; j < frating.size(); j++){
                if(frating.get(i).compareTo(frating.get(j)) == 1){
                    Rating temp = frating.get(i);
                    frating.set(i, frating.get(j));
                    frating.set(j, temp);
                }
            }
        }*/
        
        for(Rating k : frating){
            System.out.println(k.getValue() + " : Time : " + MovieDatabase.getMinutes(k.getItem()) + "  " +  MovieDatabase.getTitle(k.getItem()) );
            System.out.println("\t" + MovieDatabase.getDirector(k.getItem()));
            
        }
    }
    
}
