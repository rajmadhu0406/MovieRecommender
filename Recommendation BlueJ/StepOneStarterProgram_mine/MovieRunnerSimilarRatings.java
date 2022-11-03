
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        //below parameter is for raterdatabase file
        FourthRatings sr = new FourthRatings("ratings_short.csv");
        int NoRaters = RaterDatabase.size();
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
    
    public void printAverageRatingsByYearAfterAndGenre(){
        AllFilters F = new AllFilters();
        F.addFilter( new GenreFilter("Romance"));
        F.addFilter( new YearAfterFilter(1980));
        
        
        FourthRatings tr = new FourthRatings();
        int NoRaters = RaterDatabase.size();
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
        }
        */
        
        for(Rating k : frating){
            System.out.println(k.getValue() + " : " + MovieDatabase.getYear(k.getItem()) +  MovieDatabase.getTitle(k.getItem()) );
            System.out.println("\t" + MovieDatabase.getGenres(k.getItem()));
            
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings fr = new  FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int NoRaters = RaterDatabase.size();
        System.out.println("Total Raters in file : " + NoRaters);
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        ArrayList<Rating> R = fr.getSimilarRatings("71", 20, 5);
        System.out.println("xxx==== " + R.size());
        for(Rating r : R){
        String s = r.getItem();
        String name = MovieDatabase.getTitle(s);
        System.out.println(name + " " + r.getValue());
      }
    }
    
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new  FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int NoRaters = RaterDatabase.size();
        System.out.println("Total Raters in file : " + NoRaters);
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> R = fr.getSimilarRatingsByFilter("964", 20, 5, f);
        
        
        for(Rating r : R){
        String s = r.getItem();
        String name = MovieDatabase.getTitle(s);
        System.out.println(name + " " + r.getValue());
      }
        /*
        for(Rating r : RM){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue() );
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
        */
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fr = new  FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int NoRaters = RaterDatabase.size();
        System.out.println("Total Raters in file : " + NoRaters);
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> R = fr.getSimilarRatingsByFilter("120", 10, 2, f);
        
         for(Rating r : R){
        String s = r.getItem();
        String name = MovieDatabase.getTitle(s);
        System.out.println(name + " " + r.getValue());
      }
        
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new  FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int NoRaters = RaterDatabase.size();
        System.out.println("Total Raters in file : " + NoRaters);
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f1 = new GenreFilter("Drama");
        Filter f2 = new MinutesFilter(80, 160);
        
        AllFilters F = new AllFilters();
        F.addFilter(f1);
        F.addFilter(f2);
        
        ArrayList<Rating> R = fr.getSimilarRatingsByFilter("168", 10, 3, F);
        
        for(Rating r : R){
        String s = r.getItem();
        String name = MovieDatabase.getTitle(s);
        System.out.println(name + " " + r.getValue());
      }
        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new  FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        int NoRaters = RaterDatabase.size();
        System.out.println("Total Raters in file : " + NoRaters);
        System.out.println("MovieDatabase size : " + MovieDatabase.size());
        
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new MinutesFilter(70, 200);
        
        AllFilters F = new AllFilters();
        F.addFilter(f1);
        F.addFilter(f2);
        
        ArrayList<Rating> R = fr.getSimilarRatingsByFilter("314", 10, 5, F);
        
        for(Rating r : R){
        String s = r.getItem();
        String name = MovieDatabase.getTitle(s);
        System.out.println(name + " " + r.getValue());
      }
        
    }
}  
