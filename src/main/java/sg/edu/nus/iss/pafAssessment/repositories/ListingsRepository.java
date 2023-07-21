package sg.edu.nus.iss.pafAssessment.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pafAssessment.models.BookingForm;
import sg.edu.nus.iss.pafAssessment.models.Vacancy;

@Repository
public class ListingsRepository {

	@Autowired
	private MongoTemplate mTemplate;

    @Autowired
    private JdbcTemplate sTemplate;

    private final String INSERTBOOKINGSQL="insert into reservation values(?,?,?,?,?,?)";
	
    //db.listings.distinct('address.country')
    public List<String> getAllCountry(){
    List<String>getAllCountry=mTemplate.findDistinct(new Query(),"address.country","listings",String.class);

    return getAllCountry;
    }

    // db.listings.find({
    //     $and: [
    //       { "address.country": "Australia" },
    //       { "accommodates": 1},
    //       {"price":{$gte:1}},
    //       {"price":{$lte:100}}
    //     ]
    //   }).sort({price:-1})

    public List<Document> getSearchResult(String country,Integer accommodates, double min,double max){

        

        Criteria c=new Criteria();
        c.andOperator(Criteria.where("address.country").regex(country,"i")
        ,Criteria.where("accommodates").gte(accommodates),
        Criteria.where("price").gte(min),
        Criteria.where("price").lte(max));

        Query query = Query.query(c).with(Sort.by(Sort.Direction.DESC,"price"));

       

        List<Document> results= mTemplate.find(query,Document.class,"listings");

        return results;
        


    }



	//TODO: Task 4

    public Document getRoomDetails(String id){
        Criteria c = Criteria.where("_id").is(id);
        

        Query query = Query.query(c);

        List<Document> result=mTemplate.find(query,Document.class,"listings");

        return result.get(0);


    }
    private final String GETROOMVACANCY="select * from acc_occupancy where acc_id = ?";
    private final String MINUSOFVACANCY="update acc_occupancy set vacancy = vacancy - ? where acc_id = ?";
	

	//TODO: Task 5

    public Vacancy getRoomVacancy(Integer id){

        // List<Vacancy> vacancy=sTemplate.query(GETROOMVACANCY,BeanPropertyRowMapper.newInstance(Vacancy.class),id);

        // return vacancy.get(0);

        List<Vacancy> vacan=new ArrayList<>();

        SqlRowSet rs=sTemplate.queryForRowSet(GETROOMVACANCY,id);

        while(rs.next()){
            Vacancy vacancy = new Vacancy();
            vacancy.setAccId(rs.getString("acc_id"));
            vacancy.setVacancy(rs.getInt("vacancy"));

            vacan.add(vacancy);

        }

        return vacan.get(0);

        
    }

    public Boolean minusRoomVacancy(Integer duration,Integer accId){
        Integer result = sTemplate.update(MINUSOFVACANCY,duration,accId);

        return result>0?true:false;
    }
    public Boolean insertReservation(BookingForm form){
        form.setResvId(UUID.randomUUID().toString().substring(0, 8));
        Integer result=sTemplate.update(INSERTBOOKINGSQL,form.getResvId(),form.getUserName(),form.getUserEmail(),Integer.parseInt(form.getId()),form.getArrivalDate(),form.getDuration());

        return result>0?true:false;
    }




}
