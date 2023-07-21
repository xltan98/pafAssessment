package sg.edu.nus.iss.pafAssessment.exception;

public class InsufficientVacancyException extends RuntimeException{
    public InsufficientVacancyException(){
        super();
    }
    public InsufficientVacancyException(String message){
        super(message);
    }

    public InsufficientVacancyException(String message,Throwable cause){
        super(message,cause);
    }
      public InsufficientVacancyException(Throwable cause){
        super(cause);
    }
}
    

