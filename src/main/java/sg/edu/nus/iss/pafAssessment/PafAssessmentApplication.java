package sg.edu.nus.iss.pafAssessment;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.pafAssessment.models.Accomodation;
import sg.edu.nus.iss.pafAssessment.models.BookingForm;
import sg.edu.nus.iss.pafAssessment.repositories.ListingsRepository;
import sg.edu.nus.iss.pafAssessment.services.ListingsService;

@SpringBootApplication
public class PafAssessmentApplication implements CommandLineRunner{

	@Autowired
	ListingsRepository lRepo;

	@Autowired
	ListingsService lServ;
	public static void main(String[] args) {
		SpringApplication.run(PafAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// for(String s:lRepo.getAllCountry()){
		// 	System.out.printf(">>>>>%s",s);
		// }

			// for(Accomodation s:lServ.getSearchResult("Australia",1,1,100)){
			// System.out.printf(">>>>>%s",s);

			//System.out.print(lRepo.getRoomVacancy(27498126));

			// BookingForm form = new BookingForm();
			// form.setDuration(5);
			// form.setId("27498126");
			// form.setUserEmail("wfwef");
			// form.setUserName("wrfqwef");

			// System.out.print(form);

			// System.out.print(lRepo.insertReservation(form));

			// System.out.print(lRepo.getSearchResult("Australia",1,1,100));
		
			// for(Document s:lRepo.getSearchResult("Australia",1,1,100)){
			// System.out.printf(">>>>>%s",s);
		}	
	}
	


