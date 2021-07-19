package api.controller;


import api.googlemaps.GoogleMaps;
import api.googlemaps.Point;
import api.model.ApiInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class ApiController {

    @PostMapping(
            path= "/",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Object> getRoute(@RequestBody ApiInput input) {
        System.out.println(input);
        GoogleMaps google = new GoogleMaps();
        Point source = input.getSource();
        Point destination = input.getDestination();
        google.setSource(source.latitude, source.longitude);
        google.setDestination(destination.latitude, destination.longitude);
        google.setNavigationMode(input.getDrivingMode());
        List<Point> a = google.getDirections();

        return ResponseEntity.status(200).body(a);
    }
}
