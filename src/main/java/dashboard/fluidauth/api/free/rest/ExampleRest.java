package dashboard.fluidauth.api.free.rest;


import dashboard.except.AnonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import dashboard.fluidauth.api.free.dto.*;

@RestController
@RequestMapping("/free")
public class ExampleRest {


    private final Logger logger = LoggerFactory.getLogger( ExampleRest.class) ;


    @RequestMapping( path = "/{id_product}", headers = {"accept=application/json"}, consumes = {"application/json"},method = { RequestMethod.GET})
    public ResponseEntity<Object> getSimple(@PathVariable("id_product") Long id){

        if ( id != 1){
            throw  new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Project Not Found");
        }
        return ResponseEntity.ok(List.of(new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }}));
    }


    @RequestMapping( path = "/second/{id_product}", headers = {"accept=application/json"}, consumes = {"application/json"},method = { RequestMethod.GET})
    public ResponseEntity<Object> getSimple2(@PathVariable("id_product") Long id) throws Exception {

        if ( id == 1){
            throw new RuntimeException("Anon Exception");
        }

        if ( id == 2) {
            throw  new Exception("Error aja");
        }

        if ( id == 3){
            throw new AnonException("Bikinan");
        }

        return ResponseEntity.ok(List.of(new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }}));
    }


    @RequestMapping( path = "/acc_one", headers = {"accept=application/json"}, consumes = {"application/json"},method = { RequestMethod.POST})
    public ResponseEntity<SimpleDTOResp> accessInternalAPI(@RequestBody SimpleDTOReq sDtReq){

        RestTemplate rst = new RestTemplate();
        rst.headForHeaders("Content-Type", "application/json");
        rst.headForHeaders("Accept", "application/json");
        Optional<SimpleDTOResp> obj = Optional.ofNullable( rst.getForEntity(URI.create("http://ADV-900082-NB:8900/free/second/5"), SimpleDTOResp.class).getBody());

        return ResponseEntity.ok(obj.orElseThrow());
    }
}
