package api.googlemaps;


import java.util.ArrayList;

public class HttpClient {

//    // one instance, reuse
//    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        GoogleMaps apiData = new GoogleMaps();
        apiData.setNavigationMode("walking".toUpperCase());


        try {
            System.out.println("Testing 1 - Send Http GET request");
            System.out.println("Testing 2 - Send Http POST request");
            apiData.setSource(19.970, 72.877);
            apiData.setDestination(19.070, 72.877);
            System.out.println(apiData.getNavigationMode());
            System.out.println(apiData.getSourceDestination());
            System.out.println(apiData.getDirections());
            System.out.println(apiData.getDirections().size());
            Point a = new Point(19.970, 72.877);
            Point b = new Point(19.070, 72.877);
            double azimuth = PointsInBetween.calculateBearing(a, b);
            ArrayList<Point> locations = PointsInBetween.getLocations(50, azimuth, a, b);
            System.out.println(locations);

        }
        finally {

        }
    }

}