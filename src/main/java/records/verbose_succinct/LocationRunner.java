package records.verbose_succinct;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class LocationRunner {
    public static void main(String[] args) throws Exception{
        // Verbose Class Style
        var locationClass = new Location(37.827, -122.423);
        System.out.println("locationClass : "+ locationClass);

        // Record Style
        var locationRecord = new LocationRecord(37.827, -122.423);
        System.out.println("locationRecord : " +  locationRecord);

        // Record components
        System.out.println("locationRecord.latitude() : "+ locationRecord.latitude());
        System.out.println("locationRecord.longitude() :"+locationRecord.longitude());

        // Are you a record?
        System.out.println("locationRecord.getClass().isRecord() : "+locationRecord.getClass().isRecord());
        System.out.println("locationClass.getClass().isRecord() : "+locationClass.getClass().isRecord());

        // record components from metadata
        printRecordComponentsIfRecord(locationRecord);
        printRecordComponentsIfRecord(locationClass);

        // record methods testing
        var location1= new LocationRecord(37.827, -122.423);
        var location2= new LocationRecord(37.827, -122.423);
        var location3= new LocationRecord(40.28, -100.23);

        System.out.println(location1.latitude());
        System.out.println(location3.longitude());
        System.out.println(location3);
        System.out.println(location1.equals(location2));
        System.out.println(location2.equals(location3));
        System.out.println(location2.hashCode());
        System.out.println(location1.hashCode());
        System.out.println(location3.hashCode());

        //




    }

    private static void printRecordComponentsIfRecord(Object location) throws Exception {
        switch(location.getClass().isRecord()){
            case true-> {
                Arrays.stream(location.getClass().getRecordComponents()).forEach(rec->{
                    var name = rec.getName();
                    var type = rec.getType();
                    Object value;
                    try {
                        value = rec.getAccessor().invoke(location);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(location.getClass().getName() +" : "+type + " " + name +" has a value of "+ value );

                });

//                System.out.println(location.getClass()+" is a record : " + Arrays.stream(location.getClass().getRecordComponents()).toList());
            }
            case false-> {
                System.out.println(location.getClass().getName()+" is a NOT record so components are  : " + location.getClass().getRecordComponents());
            }
        }
    }
}
