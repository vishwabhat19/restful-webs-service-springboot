package com.rest.webservice.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
    
    @GetMapping("v1/person")
    public PersonV1 personV1() {
        return new PersonV1("V1 Person!");
    }

    @GetMapping("v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("V2First", "V2Last"));
    }

    @GetMapping(value="person/param", params="version=1")
    public PersonV1 paramV1() {
        return new PersonV1("V1 Person!");
    }

    @GetMapping(value="person/param", params="version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("V2First", "V2Last"));
    }
}
