package dashboard.fluidauth.api.location.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OfficeController {

    @RequestMapping( value = "/v1", method = RequestMethod.GET)
    public String getSimple(){
        return "ini aja";
    }
}
