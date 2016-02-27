package controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dto.Item;
import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EtsyController {

    @RequestMapping(value = "/etsy/test", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String testEtsy () throws UnirestException {
        Item i = new Item("Bike", 10);
        System.out.println(i) ;

        String key = System.getProperty("ETSY_KEYSTRING");

        if (key==null) {
            key = System.getenv("ETSY_KEYSTRING");
        }

        // Invoke unirest
        HttpResponse<String> response = Unirest.get("https://openapi.etsy.com/v2/listings/active")
                .queryString("api_key", key)
                .asString();

//        JSONArray array = new JSONArray(response.getBody());
        return response.getBody();
    }

    @RequestMapping(value = "/etsy", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Item addItemToEbay(@RequestParam(value = "name") String name, @RequestParam(value = "price") double price) {
        Item i = new Item(name, price);
        System.out.println(i) ;
        return i;
    }

}