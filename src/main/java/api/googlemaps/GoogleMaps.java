package api.googlemaps;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;

import java.util.*;


enum DrivingModes {
    DRIVING,
    WALKING,
    BICYCLING,
}


public class GoogleMaps {
    private static final String accessKey = Optional.ofNullable(System.getenv("GOOGLE_MAPS_KEY")).
            orElse("AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos");
    private GeoApiContext context;
    Point source;
    Point destination;
    private TravelMode mode;

    public GoogleMaps() {
        this.context = new GeoApiContext.Builder()
                .apiKey(accessKey)
                .build();
        this.source = new Point();
        this.destination = new Point();
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


    public boolean setSource(double latitude, double longitude) {
        try {
            this.source.setLatitude(latitude);
            this.source.setLongitude(longitude);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean setDestination(double latitude, double longitude) {
        try {
            this.destination.setLatitude(latitude);
            this.destination.setLongitude(longitude);
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Map<String, Point> getSourceDestination() {
        Map<String, Point> data = new HashMap<String, Point>();
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

    public List<Point>  getDirections() {
        List<Point> path = new ArrayList();
        ArrayList<Map<String, Double>> data = new ArrayList<Map<String, Double>>();
        try {
            String from = String.format("%s, %s", String.valueOf(this.source.latitude), String.valueOf(this.source.longitude));
            String to = String.format("%s, %s", String.valueOf(this.destination.latitude), String.valueOf(this.destination.longitude));
            System.out.println(String.format("%s : %s", from, to));
            DirectionsApiRequest results = DirectionsApi.getDirections(this.context, from, to).mode(this.mode);
            DirectionsResult apiResult = results.await();

            if (apiResult.routes != null && apiResult.routes.length > 0) {
                DirectionsRoute route = apiResult.routes[0];
                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        for (int j = 0; j < leg.steps.length; j++) {
                            DirectionsStep step = leg.steps[j];
                            if (step.steps != null && step.steps.length > 0) {
                                for (int k = 0; k < step.steps.length; k++) {
                                    DirectionsStep step1 = step.steps[k];
                                    EncodedPolyline points1 = step1.polyline;
                                    if (points1 != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                        for (com.google.maps.model.LatLng coord1 : coords1) {
                                            path.add(new Point(coord1.lat, coord1.lng));
                                        }
                                    }
                                }
                            } else {
                                EncodedPolyline points = step.polyline;
                                if (points != null) {
                                    //Decode polyline and add points to list of route coordinates
                                    List<com.google.maps.model.LatLng> coords = points.decodePath();
                                    for (com.google.maps.model.LatLng coord : coords) {
                                        path.add(new Point(coord.lat, coord.lng));
                                    }
                                }
                            }

                        }

                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println(e);
            // wait for await
        }
        finally {
            System.out.println(path.size());
            return path;
        }

    }
}

