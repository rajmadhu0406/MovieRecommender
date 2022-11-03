package mycode;

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.util.concurrent.*;
public class Runner {

    public static void main (String[] args)
    {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        ArrayList<String> AllMovieList = MovieDatabase.filterBy(new TrueFilter());
        int Msize = AllMovieList.size();
        
        /*
        for(int i = 0; i < 100; i++)
        {
            System.out.println("-" + MovieDatabase.getTitle(AllMovieList.get(i)) + "-" );
        }
        */
        
        int RID = RaterDatabase.size();
        RID = RID + 1;
        String RaterID = Integer.toString(RID);
        RaterID = RaterID.trim();
        
        // addRaterRating(String raterID, String movieID, double rating)
        Scanner sc = new Scanner(System.in);
        
        //String[] movielist = {"The Dictator","Grown Ups 2","The Maze Runner","Inception",
        //    "Life of Pi","The Amazing Spider-Man 2","Iron Man 3","Moneyball","Cars","The Girl Next Door"};
        //The Dictator || Grown Ups 2    comedy
        //The Maze Runner || Inception          Sci-Fi
        //Life of Pi    ||  Adventure
        //The Amazing Spider-Man 2 ||  Iron Man 3  Fantasy
        //Moneyball  Biography
        //The Girl Next Door || Cars
        
        System.out.println("Do you want to rate movies of your choice (yes) or provided by us (no) ? (Type 'yes'/ 'no')"); 
        String reply = sc.next();
        //reply = reply.toLowerCase();
        
        switch(reply)
        {
            
            case "yes":
 
            for(int i = 0; i < 10; i++)
            {
                
                
                String MId = new String();
                sc.nextLine(); //clearing buffer
                while(true)
                {
                    System.out.print("Enter Movie name : ");
                    String name = sc.nextLine();
                    
                    name = name.replaceAll("\\s","");
                    name = name.toLowerCase();
                    //System.out.println("-" + name + "-");
                    //name = name.trim();
                    MId = MovieDatabase.getMovieID(name);
                    
                    
                    if(MId.equals("!!!"))
                    {
                        System.out.println("Error!!! : Enter valid name ==" + MId + "==");
                    }
                    else
                    {
                        break;
                    }
                }
                System.out.println("Your ratings : ");
                double rating = sc.nextDouble();
                
                RaterDatabase.addRaterRating(RaterID, MId, rating);
                System.out.println("\n");
            }
            System.out.println("\n");
            break;
            
            case "no":
        
            Random random = new Random();
            
            for(int i = 0; i < 10; i++)
            {
                int rand = random.nextInt(Msize);
                String MId = AllMovieList.get(rand);
                String name = MovieDatabase.getTitle(MId);
                System.out.println("Rate movie :  " + name );
                double rating = sc.nextDouble();
                
                RaterDatabase.addRaterRating(RaterID, MId, rating);
                System.out.println("\n");
            }
            System.out.println("\n");
           
            break;
        }
        
            FourthRatings fr = new  FourthRatings("ratings.csv");
            //RaterID , limit, minRaters
            //R will give Rating object of movies with [String ID, Weighted rating]
            ArrayList<Rating> R = fr.getSimilarRatings(RaterID, 100, 5);
            
            System.out.println("Here are some movies recommanded especially for you :\n");
            
            for(int k = 0; k < R.size(); k++)
            {
                String movieID = R.get(k).getItem();
                String title = MovieDatabase.getTitle(movieID);
                double ratings = R.get(k).getValue();
                int year = MovieDatabase.getYear(movieID);
                String genres = MovieDatabase.getGenres(movieID);
                System.out.println("-" + title + "- " + " (" + year + ") "+ genres +" : " +  ratings);
            }
            
    }
    
}
