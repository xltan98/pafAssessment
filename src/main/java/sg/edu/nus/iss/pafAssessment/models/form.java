package sg.edu.nus.iss.pafAssessment.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class form {
    @NotNull(message="country cannot be empty")
    private String country;

    @Min(value=1, message="minimum value is 1")
    @Max(value=100, message="maximum value is 10")
    @Digits(integer=3,fraction=0,message="please select correct values")
    private Integer noOfPerson;

    @Min(value=1, message="min value is 1")
    @Digits(integer=3,fraction=1,message="please put correct value")
    private Double minPrice;
    @Max(value=10000, message="max value is 10000")
    @Digits(integer=3,fraction=1,message="please put correct value")
    private Double maxPrice;

    
}