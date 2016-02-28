package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import javax.xml.bind.annotation.XmlRootElement;

@Data @NoArgsConstructor @AllArgsConstructor @XmlRootElement
public class Item {
    private String name;
    private String description;
    private double price;

}
