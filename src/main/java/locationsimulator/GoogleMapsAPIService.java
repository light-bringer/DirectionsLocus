package locationsimulator;
//
//import com.google.maps.GeoApiContext;
//import com.google.maps.PlacesApi;
//import com.google.maps.model.AutocompletePrediction;
//import com.google.maps.model.LatLng;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class GoogleMapsAPIService {
//    public List placeAutoComplete(String input, Double latitude, Double longitude, String language) {
//        GeoApiContext context = new GeoApiContext().setApiKey("");
//        LatLng location = new LatLng(latitude, longitude); // -23.52488881961586,-46.67578987777233
//
//        AutocompletePrediction[] autocompletePredictions = PlacesApi.placeAutocomplete(context, input)
//                .location(location)
//                .language("pt-BR")
//                .radius(1000)
//                .awaitIgnoreError();
//
//        return assemblyPrediction(autocompletePredictions);
//    }
//
//    private List assemblyPrediction(AutocompletePrediction[] autoCompletePredictions) {
//        List<Prediction> predictions = new ArrayList<>();
//
//        for (AutocompletePrediction autocompletePrediction : autoCompletePredictions) {
//            String placeId = autocompletePrediction.placeId;
//            String title = autocompletePrediction.terms[0].value;
//            String subTitle = Arrays.stream(autocompletePrediction.terms).skip(1).map(term -> term.value).collect(Collectors.joining(", "));
//
//            predictions.add(new Prediction(placeId, title, subTitle));
//        }
//
//        return predictions;
//    }
//}
//
