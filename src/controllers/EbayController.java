package controllers;


import dto.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EbayController {

    @RequestMapping(value = "/ebay", method = RequestMethod.PUT) @ResponseBody
    public String addItemToEbay(@RequestBody String name, @RequestBody String price) {
        System.out.println("Adding item to ebay.");
        Item i = new Item (name, Double.valueOf(price));
        System.out.println(i);
        return i.toString();
//        return new ModelAndView("index");
    }




}