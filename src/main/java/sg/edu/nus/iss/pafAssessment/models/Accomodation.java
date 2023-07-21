package sg.edu.nus.iss.pafAssessment.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accomodation {

    private String addressStreet;
    private String name;
    private String price;
    private String id;
    private String description;
    private String addressSuburb;
    private String addressCountry;
    private String imagesPictureUrl;
    
    private String amenities;
    
}
