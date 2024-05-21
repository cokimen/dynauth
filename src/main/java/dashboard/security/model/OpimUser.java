package dashboard.security.model;


import jakarta.persistence.Entity;

@Entity
public class OpimUser {

    private Long id;
    private String username;
    private String email;

}
