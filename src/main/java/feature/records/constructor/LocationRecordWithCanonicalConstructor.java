package feature.records.constructor;

public record LocationRecordWithCanonicalConstructor(double latitude, double longitude) {
    /*
    // canonical constructor
    public LocationRecordWithCanonicalConstructor(double latitude, double longitude) { //not preferrable
        if(latitude < -90 || latitude > 90 ||
                longitude < -180 || longitude > 180) {
            throw new RuntimeException("The location is out of this world");
        }
        this.latitude = Math.round(latitude * 100.0) / 100.0;
        this.longitude = longitude;
    }*/

    // Compact Constructor
    public LocationRecordWithCanonicalConstructor{
        if(latitude < -90 || latitude > 90 ||
                longitude < -180 || longitude > 180) {
            throw new RuntimeException("The location is out of this world");
        }
        latitude = Math.round(latitude * 100.0) / 100.0;
    }
    // Non Canonical Constructor
    public LocationRecordWithCanonicalConstructor(String position){
        this(Double.parseDouble(position.split(":")[0]),Double.parseDouble(position.split(":")[1]));
    }

    public static void main(String[] args) {
        try {
            var locationRecordWithCanonicalConstructor =
                    new LocationRecordWithCanonicalConstructor(37.827, -222.423);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }

        var locationRecordWithCanonicalConstructor1 =
                new LocationRecordWithCanonicalConstructor(37.23213, 170.12313);
        System.out.println(locationRecordWithCanonicalConstructor1.latitude());
    }
}
