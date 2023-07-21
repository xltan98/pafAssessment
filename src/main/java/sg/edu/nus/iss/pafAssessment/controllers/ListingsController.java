package sg.edu.nus.iss.pafAssessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.pafAssessment.models.Accomodation;
import sg.edu.nus.iss.pafAssessment.models.BookingForm;
import sg.edu.nus.iss.pafAssessment.models.Vacancy;
import sg.edu.nus.iss.pafAssessment.repositories.ListingsRepository;
import sg.edu.nus.iss.pafAssessment.services.ListingsService;

@Controller
@RequestMapping
public class ListingsController {

    @Autowired
    ListingsService lServ;

    @GetMapping("/home")
    public String getCountry(Model m,String globalError){
        List<String> countryList=lServ.getAllCountry();
        m.addAttribute("countryList",countryList);
        m.addAttribute("globalError",globalError);

        return "view1";
    }

    @GetMapping("/search")
    public String getSearchResult(@RequestParam String country,@RequestParam Integer accomodates, @RequestParam double min,@RequestParam double max,Model m){
        List<Accomodation> accomodationList=lServ.getSearchResult(country,accomodates,min,max);
        m.addAttribute("accomodationList",accomodationList);


        m.addAttribute("country",country);
        m.addAttribute("accomodates",accomodates.toString());
        m.addAttribute("min",min);
        m.addAttribute("max",max);

        if(country.isEmpty()){
            return "redirect:/home?globalError=Country cannot be empty";
        }
        if(accomodates<1 || accomodates>10){
            return "redirect:/home?globalError=No of ppl must be between 1 and 10";
        }
        if(min<1 || max>1000){
            return "redirect:/home?globalError=Price must be within 1 and 10000";
        }


        return "view2";
    }

    @GetMapping("/detail/{id}")
    public String getDetails(@PathVariable String id, Model m){
        Accomodation accomodation=lServ.getRoomDetails(id);

        m.addAttribute("accomodation",accomodation);
        m.addAttribute("form",new BookingForm());
        // s.setAttribute("id",id);

        return "view3";
    }

    @PostMapping("/booked")
    public String getBookResult(@ModelAttribute BookingForm form,Model m){

        System.out.print(form);
        Integer id= Integer.parseInt(form.getId());
        Vacancy roomvacancy=lServ.getRoomVacancy(id);
        String bookingId=lServ.reserveRoom(roomvacancy,form);

        m.addAttribute("bookingId", bookingId);

        return "view4";
        
    }

    
}
