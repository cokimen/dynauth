package dashboard.fluidauth.api.location.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joiner")
public class JoinerController {


    @RequestMapping(value = "/oke", method = RequestMethod.GET)
    public String getJoinerDef() {
        return "getJoiner";
    }
}
