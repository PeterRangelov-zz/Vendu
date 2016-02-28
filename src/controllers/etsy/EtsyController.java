package controllers.etsy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dto.Item;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EtsyController {
    private String key;

    @RequestMapping(value = "/etsy/test", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String testEtsy () throws UnirestException {
        Item i = new Item("Bike", 10);
        System.out.println(i) ;
        String key = System.getenv("ETSY_KEYSTRING");
        HttpResponse<String> response = Unirest.get("https://openapi.etsy.com/v2/listings/active")
                .queryString("api_key", key)
                .asString();
        return response.getBody();
    }

    @RequestMapping(value = "/etsy/items/{shop}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONArray getAllItemsForUser (@PathVariable(value = "shop") String shop) throws UnirestException, ParseException {
        Item i = new Item("Bike", 10);
        key = System.getenv("ETSY_KEYSTRING");

        HttpResponse<String> response = Unirest.get("https://openapi.etsy.com/v2/shops/" + shop + "/listings/active")
                .queryString("api_key", key)
                .asString();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.getBody());

        JSONArray jsonResponse = (JSONArray) json.get("results");

        return jsonResponse;
    }






    @RequestMapping(value = "/etsy", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public Item addItemToEbay(@RequestParam(value = "name") String name, @RequestParam(value = "price") double price) {
        Item i = new Item(name, price);
        System.out.println(i) ;
        return i;
    }

}