package dashboard.fluidauth.api.location.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myjdbc")
public class JdbcAuthController {


    @RequestMapping(value = "/oke", method = RequestMethod.GET)
    public String getJoinerDef() {
        return "getJoiner";
    }
}
