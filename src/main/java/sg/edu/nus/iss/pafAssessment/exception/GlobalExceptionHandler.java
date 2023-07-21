package sg.edu.nus.iss.pafAssessment.exception;



    import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientVacancyException.class)
    public String handleException(InsufficientVacancyException ex,HttpServletRequest request,HttpServletResponse response,Model model){
        response.setStatus(500);
        ApiError errMsg= new ApiError(response.getStatus(),new Date(),ex.getMessage()+ " "+request.getRequestURI());
        model.addAttribute("errMsg", errMsg);

        return "error";
    }
    
}
    

