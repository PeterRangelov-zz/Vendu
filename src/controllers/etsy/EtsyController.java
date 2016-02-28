package controllers.etsy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dto.Item;
import dto.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.service.ServiceRegistryBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import util.DbUtil;

import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class EtsyController {
    private final String key = System.getenv("ETSY_KEYSTRING");

    @RequestMapping(value = "/etsy/test", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE) @ResponseBody
    public  String etsyPulseCheck () throws UnirestException {
        Item i = new Item();
        HttpResponse<String> response = Unirest.get("https://openapi.etsy.com/v2/listings/active")
                .queryString("api_key", key)
                .asString();
        User u = new User();


        DbUtil dbUtil = new DbUtil();
        Session session = dbUtil.getFactory().openSession();


        try {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }









        return response.getBody();
    }

    @RequestMapping(value = "/etsy/items/{shop}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE) @ResponseBody
    public ArrayList<Item> getAllItemsForUser (@PathVariable(value = "shop") String shop) throws UnirestException, ParseException {
        ArrayList<Item> items = new ArrayList<>();

        HttpResponse<String> response = Unirest.get("https://openapi.etsy.com/v2/shops/" + shop + "/listings/active")
                .queryString("api_key", key)
                .asString();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.getBody());

        JSONArray jsonResponse = (JSONArray) json.get("results");
        Iterator itr = jsonResponse.iterator();
        while(itr.hasNext()) {
            JSONObject element = (JSONObject) itr.next();
            Item i = new Item();
            i.setName((String) element.get("title"));
            i.setPrice(Double.parseDouble((String) element.get("price")));
            i.setDescription((String) element.get("description"));
            items.add(i);
        }

        return items;
    }

//    @RequestMapping(value = "/etsy", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
//    public Item addItemToEbay(@RequestParam(value = "name") String name, @RequestParam(value = "price") double price) {
//        Item i = new Item(name, price);
//        System.out.println(i) ;
//        return i;
//    }

}