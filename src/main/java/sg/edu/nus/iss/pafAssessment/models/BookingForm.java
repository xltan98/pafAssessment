package sg.edu.nus.iss.pafAssessment.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingForm {

    private String userName;
    private String userEmail;
    private Date arrivalDate;
    private Integer duration;
    private String id;
    private String resvId;
    
}
