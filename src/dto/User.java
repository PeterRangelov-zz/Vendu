package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import javax.persistence.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue private int id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String passwordHash;
    private DateTime dateJoined;
    private boolean accountActivated;

    private String etsyStore;

}
