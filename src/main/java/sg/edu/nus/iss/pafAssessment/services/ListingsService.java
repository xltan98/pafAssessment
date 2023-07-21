package sg.edu.nus.iss.pafAssessment.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonObject;
import org.bson.json.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import sg.edu.nus.iss.pafAssessment.models.Accomodation;
import sg.edu.nus.iss.pafAssessment.models.BookingForm;
import sg.edu.nus.iss.pafAssessment.models.Vacancy;
import sg.edu.nus.iss.pafAssessment.repositories.ListingsRepository;

@Service
public class ListingsService {
	@Autowired
    ListingsRepository lRepo;
	//TODO: Task 2

    public List<String> getAllCountry(){
        return lRepo.getAllCountry();
        
    }
	
	//TODO: Task 3
    public List<Accomodation> getSearchResult(String country,Integer accomodates, double min,double max){
    List<Document> docs=lRepo.getSearchResult(country,accomodates,min,max);    
        List<Accomodation> accomodationList= new ArrayList<>();
        for(Document doc:docs){
            Accomodation a= new Accomodation();
            a.setName(doc.getString("name"));
            Document address=doc.get("address",Document.class);
                a.setAddressCountry(address.getString("country"));
                a.setAddressStreet(address.getString("street"));
                a.setAddressSuburb(address.getString("suburb"));
            a.setId(doc.getString("_id"));
            a.setDescription(doc.getString("description"));
                Document images=doc.get("images",Document.class);
                a.setImagesPictureUrl(images.getString("picture_url"));
            a.setPrice(doc.get("price").toString());
           // a.setAmenities((List<String>) doc.get("ammenities"));

            // a.setAmenities(doc.getList("ammenities"));

            

            accomodationList.add(a);

        }

    

        return accomodationList;
    }


	//TODO: Task 4


    public Accomodation getRoomDetails(String id){
        Document doc=lRepo.getRoomDetails(id);

         Accomodation a= new Accomodation();
            a.setName(doc.getString("name"));
            Document address=doc.get("address",Document.class);
                a.setAddressCountry(address.getString("country"));
                a.setAddressStreet(address.getString("street"));
                a.setAddressSuburb(address.getString("suburb"));
            a.setId(doc.getString("_id"));
            a.setDescription(doc.getString("description"));
                Document images=doc.get("images",Document.class);
                a.setImagesPictureUrl(images.getString("picture_url"));
            a.setPrice(doc.get("price").toString());
            // JsonReader read=Json.createReader(new StringReader(doc.toJson()))
            
            a.setAmenities(doc.get("amenities").toString());

            return a;
    }
	

	//TODO: Task 5
    public Vacancy getRoomVacancy(Integer i){
        return lRepo.getRoomVacancy(i);

    }
    @Transactional
    public String reserveRoom(Vacancy vacancy,BookingForm form){
        Boolean bEnoughVacancy=false;
        if(vacancy.getVacancy()>form.getDuration()){
            lRepo.minusRoomVacancy(Integer.parseInt(form.getId()),form.getDuration());
            bEnoughVacancy=true;
        }
        if(bEnoughVacancy){
            if(true){
                lRepo.insertReservation(form);
                return form.getResvId();
            }
            
        }
        else{
            if(!bEnoughVacancy){
                //throw error here
                return "error";
            }
       
        }
        return null;



    }}


