package googlemaps;


import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;


enum DrivingModes {
    DRIVING,
    WALKING,
    BICYCLING,
}


public class GoogleMaps {
    private static final String accessKey = Optional.ofNullable(System.getenv("GOOGLE_MAPS_KEY")).
            orElse("AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos");
    private GeoApiContext context;
    private Map<String, Double> source;
    private Map<String, Double> destination;
    private TravelMode mode;

    public GoogleMaps() {
        this.context = new GeoApiContext.Builder()
                .apiKey(accessKey)
                .build();
        this.source = new HashMap<>();
        this.destination = new HashMap<>();

        this.source.put("latitude", 0.0);
        this.source.put("longitude", 0.0);
        this.destination.put("latitude", 0.0);
        this.destination.put("longitude", 0.0);
    }
    protected void finalize() {
        try {
            this.context.close();
        }
        catch (Exception e) {

        }
        finally {
            System.out.println("GoodBye!");
        }
    }


    public boolean setSource(String latitude, String longitude) {
        try {
            this.source.put("latitude", Double.parseDouble(latitude));
            this.source.put("longitude", Double.parseDouble(longitude));
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean setDestination(String latitude, String longitude) {
        try {
            this.destination.put("latitude", Double.parseDouble(latitude));
            this.destination.put("longitude", Double.parseDouble(longitude));
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Map<String, Map<String, Double>> getSourceDestination() {
        Map<String, Map<String, Double>> data = new HashMap<String, Map<String, Double>>();
        data.put("Source", this.source);
        data.put("Destination", this.destination);
        return data;
    }

    public boolean setNavigationMode(String drivingMode) {
        try {
            DrivingModes mode = DrivingModes.valueOf(drivingMode);
            System.out.println(mode);
            switch (mode) {
                case DRIVING:
                    this.mode = TravelMode.DRIVING;
                    return true;
                case WALKING:
                    this.mode = TravelMode.WALKING;
                    return true;
                case BICYCLING:
                    this.mode = TravelMode.BICYCLING;
                    return true;
                default:
                    System.out.println("Mode not between DRIVING/WALKING/BICYCLING");
                    return false;
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Mode not between DRIVING/WALKING/BICYCLING");
            return false;
        }
    }
    public String getNavigationMode() {
        if (this.mode != null) {
            return this.mode.toString();
        }
        else {
            return "mode not set properly";
        }
    }

    public ArrayList<Map<String, String>> getDirections() {
        ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
        try {
            String from = String.format("%s, %s", this.source.get("latitude"), this.source.get("longitude"));
            String to = String.format("%s, %s", this.destination.get("latitude"), this.destination.get("longitude"));
            System.out.println(String.format("%s : %s", from, to));
            DirectionsApiRequest results = DirectionsApi.getDirections(this.context, from, to).mode(this.mode);
            DirectionsResult apiResult = results.await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(Arrays.stream(apiResult.geocodedWaypoints)));
        }
        catch (Exception e) {
            System.out.println(e);
            // wait for await
        }
        finally {
            //do something
        }

        return data;
    }

}

