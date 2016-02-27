package controllers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dto.Item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EtsyController {

    @RequestMapping(value = "/etsy/test", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Item testEtsy () throws UnirestException {
        Item i = new Item("Bike", 10);
        System.out.println(i) ;

        // Invoke unirest
        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
                .queryString("name", "Mark")
                .field("last", "Polo")
                .asJson();


        return i;
    }

    @RequestMapping(value = "/etsy", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Item addItemToEbay(@RequestParam(value = "name") String name, @RequestParam(value = "price") double price) {
        Item i = new Item(name, price);
        System.out.println(i) ;
        return i;
    }

}