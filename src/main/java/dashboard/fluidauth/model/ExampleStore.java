package dashboard.fluidauth.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Timer;

@Data
@NoArgsConstructor
//@Entity
public class ExampleStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String store_name;

//    private String
//    private Timer open_hour;

//    private Timer close_hour;


}
