package io.github.javafaktura.s02e03.child.api;

import io.github.javafaktura.s02e03.child.core.model.*;
import io.github.javafaktura.s02e03.child.core.service.ChildNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ChildNameRestController {

    private final ChildNameService childNameService;

    public ChildNameRestController(ChildNameService childNameService) {
        this.childNameService = childNameService;
    }

    @RequestMapping(path = "/child-names")
    public List<ChildNameStats> all(@RequestParam(required = false) Gender gender,
                                    @RequestParam(required = false) Popularity popularity) {
        return childNameService.getAll(new ParentPreferences(gender, popularity));
    }

    @RequestMapping(path = "/child-names", method = RequestMethod.POST)
    public ChildNameStats choose(@RequestBody ParentChoice choice) {
        return childNameService.add(choice.getName().toUpperCase());
    }

    @RequestMapping(path = "/child-names/{name}")
    public ChildNameStats lookFor(@PathVariable String name) {
        return childNameService.lookFor(name.toUpperCase());
    }

    @RequestMapping(path = "/child-names/random")
    public ChildNameStats random(@RequestParam(required = false) Gender gender,
                                @RequestParam(required = false) Popularity popularity) {
        return childNameService.getRandom(new ParentPreferences(gender, popularity));
    }

    @RequestMapping(path = "/child-names/{name}/history")
    public ResponseEntity<ChildNameHistoricalStats> historicalStats(@PathVariable String name) {
        return ResponseEntity.of(childNameService.getHistoricalStats(name.toUpperCase()));
    }
}



