package dashboard.fluidauth.api.location.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webapp")
public class WebDummyAppController {

    @RequestMapping(value = "/oke", method = RequestMethod.GET)
    public String trialAja(){
        return "trial aja";
    }
}
