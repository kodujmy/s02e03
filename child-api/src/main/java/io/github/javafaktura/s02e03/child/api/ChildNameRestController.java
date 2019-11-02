package io.github.javafaktura.s02e03.child.api;

import io.github.javafaktura.s02e03.child.core.model.*;
import io.github.javafaktura.s02e03.child.core.service.ChildNameMemoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChildNameRestController {

    private final ChildNameMemoryService childNameService;

    public ChildNameRestController(ChildNameMemoryService childNameService) {
        this.childNameService = childNameService;
    }

    @RequestMapping("/child-names/random")
    public ChildNameStats index(@RequestParam(required = false) Gender gender,
                                @RequestParam(required = false) Popularity popularity,
                                Model model) {
        return childNameService.getRandom(new ParentPreferences(gender, popularity));
    }

    @RequestMapping(value = "/child-names/{name}")
    public ChildNameStats name(@PathVariable String name, Model model) {
        return childNameService.lookFor(name.toUpperCase());
    }

    @RequestMapping("/child-names")
    public List<ChildNameStats> all(Model model) {
        return childNameService.getAll();
    }

    @RequestMapping(value = "/child-names/choose", method = RequestMethod.POST)
    public ChildNameStats choose(@RequestBody ParentChoice choice) {
        return childNameService.add(choice.getName().toUpperCase());
    }
}
