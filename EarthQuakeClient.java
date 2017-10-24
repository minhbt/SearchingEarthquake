import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe:quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
       for(QuakeEntry qe:quakeData){
            Location loc= qe.getLocation();
            if(loc.distanceTo(from)<distMax){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth) {
         ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
       for(QuakeEntry qe:quakeData){
            if(qe.getDepth()<maxDepth && qe.getDepth()>minDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    
    
     public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where, String phrase) {
         ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
       
       for(QuakeEntry qe:quakeData){
            String title= qe.getInfo();
            
            where= where.toLowerCase();
           // phrase= phrase.toLowerCase();
            
            if(title.indexOf(where)<0){
                switch(where){
                    case "start":
                        if(title.substring(0,phrase.length()).equals(phrase)){
                            answer.add(qe);
                        }
                        break;
                    case "end":
                        if(title.length()>phrase.length()){
                            if(title.substring(title.length()-phrase.length(),title.length()).equals(phrase)){
                                answer.add(qe);
                            }
                        }
                        break;
                    case "any":
                        if (title.contains(phrase)){
                            answer.add(qe);
                        }
                        break;
                    
                }
                
                
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*for(QuakeEntry qe:list){
            if(qe.getMagnitude()<5.0){
                System.out.println(qe);
            }
        }*/
        
        ArrayList<QuakeEntry> listBig= filterByMagnitude(list, 5.0);
        for(QuakeEntry qe: listBig){
            System.out.println(qe);
        }

    }
    
    
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
 
        // TODO
        ArrayList<QuakeEntry> listBig= filterByDepth(list, -4000.0  , -2000.0 );
        for(QuakeEntry qe: listBig){
            System.out.println(qe);
        }
        
        System.out.println(listBig.size());
    
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        // TODO
        ArrayList<QuakeEntry> listBig= filterByPhrase(list, "any" , "Can" );
        //ArrayList<QuakeEntry> listBig= filterByPhrase(list, "any" , "Creek" );
        //ArrayList<QuakeEntry> listBig= filterByPhrase(list, "start" , "Explosion" );
        for(QuakeEntry qe: listBig){
            System.out.println(qe);
        }
        System.out.println(listBig.size());
    
    
    }
    
    
    
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> listBig= filterByDistanceFrom(list, 1000*1000, city);
        for(QuakeEntry qe: listBig){
            System.out.println(qe);
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
