package io.github.javafaktura.s02e03.child.webapp;

import org.springframework.stereotype.Controller;

@Controller
public class ChildNameController {

    private final ChildNameClientService childNameClientService;

    public ChildNameController(ChildNameClientService childNameClientService) {
        this.childNameClientService = childNameClientService;
    }
    /** client-/ **/

    /** client-/{name} **/

    /** client-/all **/

    /** client-/choice **/

}
