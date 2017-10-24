 import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    
     public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source= "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_hour.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> largs = getLargest(list,50);
        for(int k=0; k < largs.size(); k++){
            QuakeEntry entry = largs.get(k);
            System.out.println(entry);
        }
        System.out.println("number found: "+largs.size());
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData) {
        int result=0;
        double largest = 0;
        for(int k=0;k<quakeData.size()-1;k++){
            QuakeEntry quake= quakeData.get(k);
            double mag= quake.getMagnitude();
            if(mag>largest){
                    largest= mag;
                    result= k;
            }
        }
        return result;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,Integer howMany){
       ArrayList<QuakeEntry> lars = new ArrayList<QuakeEntry>();
        // TO DO
        int larIndex= indexOfLargest(quakeData); 
        ArrayList<QuakeEntry> quakeDataSorted= new ArrayList<QuakeEntry>();
        
        for(int k=quakeData.size()-1;k>=0;k--){
             quakeDataSorted.add(quakeData.get(larIndex));
             quakeData.remove(larIndex);
             larIndex= indexOfLargest(quakeData); 
        }
        
        for(int i=0; i<howMany;i++){
            int minIndex=0;
            lars.add(quakeDataSorted.get(i));   
        }
        return lars;
         
    }
}
