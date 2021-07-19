package api.model;


import api.googlemaps.Point;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApiInput {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NotBlank(message = "Source is mandatory")
    Point source;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NotBlank(message = "Destination is Mandatory")
    Point destination;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    Integer interval;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @NotBlank(message = "Driving Mode is Mandatory")
    String drivingMode;

    //Getters and setters




    @Override
    public String toString() {
        return "Employee [id=" + this.destination + ", firstName=" + this.source + "," + "]";
    }
}

