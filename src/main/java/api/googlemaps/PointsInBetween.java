package api.googlemaps;

import java.util.ArrayList;

public class PointsInBetween {
    private static final long RADIUS_OF_EARTH = 6371000; // radius of earth in m

//    static ArrayList<Point> getAllPointsBetween(Point one, Point two) {
//
//    }


    /**
     * returns every coordinate pair in between two coordinate pairs given the desired interval
     * @param interval
     * @param azimuth
     * @param start
     * @param end
     * @return
     */
    public static ArrayList<Point> getLocations(int interval, double azimuth, Point start, Point end) {
        System.out.println("getLocations: " +
                "\ninterval: " + interval +
                "\n azimuth: " + azimuth +
                "\n start: " + start.toString());

        double d = getPathLength(start, end);
        int dist = (int) d / interval;
        int coveredDist = interval;
        ArrayList<Point> coords = new ArrayList<>();
        coords.add(new Point(start.latitude, start.longitude));
        for(int distance = 0; distance < dist; distance += interval) {
            Point coord = getDestinationLatLng(start.latitude, start.longitude, azimuth, coveredDist);
            coveredDist += interval;
            coords.add(coord);
        }
        coords.add(new Point(end.latitude, end.longitude));

        return coords;

    }

    /**
     * calculates the distance between two lat, long coordinate pairs
     * @param start
     * @param end
     * @return
     */
    public static double getPathLength(Point start, Point end) {
        double lat1Rads = Math.toRadians(start.latitude);
        double lat2Rads = Math.toRadians(end.latitude);
        double deltaLat = Math.toRadians(end.latitude - start.latitude);

        double deltaLng = Math.toRadians(end.longitude - start.longitude);
        double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.cos(lat1Rads) * Math.cos(lat2Rads) * Math.sin(deltaLng/2) * Math.sin(deltaLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = RADIUS_OF_EARTH * c;
        return d;
    }

    /**
     * returns the lat an long of destination point given the start lat, long, aziuth, and distance
     * @param lat
     * @param lng
     * @param azimuth
     * @param distance
     * @return
     */
    public static Point getDestinationLatLng(double lat, double lng, double azimuth, double distance) {
        double radiusKm = RADIUS_OF_EARTH / 1000; //Radius of the Earth in km
        double brng = Math.toRadians(azimuth); //Bearing is degrees converted to radians.
        double d = distance / 1000; //Distance m converted to km
        double lat1 = Math.toRadians(lat); //Current dd lat point converted to radians
        double lon1 = Math.toRadians(lng); //Current dd long point converted to radians
        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(d / radiusKm) + Math.cos(lat1) * Math.sin(d / radiusKm) * Math.cos(brng));
        double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(d / radiusKm) * Math.cos(lat1), Math.cos(d / radiusKm) - Math.sin(lat1) * Math.sin(lat2));
        //convert back to degrees
        lat2 = Math.toDegrees(lat2);
        lon2 = Math.toDegrees(lon2);
        return new Point(lat2, lon2);
    }

    /**
     * calculates the azimuth in degrees from start point to end point");
     double startLat = Math.toRadians(start.lat);
     * @param start
     * @param end
     * @return
     */
    public static double calculateBearing(Point start, Point end) {
        double startLat = Math.toRadians(start.latitude);
        double startLong = Math.toRadians(start.longitude);
        double endLat = Math.toRadians(end.latitude);
        double endLong = Math.toRadians(end.longitude);
        double dLong = endLong - startLong;
        double dPhi = Math.log(Math.tan((endLat / 2.0) + (Math.PI / 4.0)) / Math.tan((startLat / 2.0) + (Math.PI / 4.0)));
        if (Math.abs(dLong) > Math.PI) {
            if (dLong > 0.0) {
                dLong = -(2.0 * Math.PI - dLong);
            } else {
                dLong = (2.0 * Math.PI + dLong);
            }
        }
        double bearing = (Math.toDegrees(Math.atan2(dLong, dPhi)) + 360.0) % 360.0;
        return bearing;
    }
}
