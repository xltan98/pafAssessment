package sg.edu.nus.iss.pafAssessment.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private Integer status;
    private Date timeStamp;
    private String message;
    
}