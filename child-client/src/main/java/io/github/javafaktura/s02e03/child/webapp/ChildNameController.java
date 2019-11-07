package io.github.javafaktura.s02e03.child.webapp;

import io.github.javafaktura.s02e03.child.client.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class ChildNameController {

    private final ChildNameConsumer childNameConsumer;

    public ChildNameController(ChildNameConsumer childNameConsumer) {
        this.childNameConsumer = childNameConsumer;
    }

    @RequestMapping("/")
    public String index(@RequestParam(required = false) Gender gender,
                        @RequestParam(required = false) Popularity popularity,
                        Model model) {
        ChildNameStats randomChildNameStats = childNameConsumer.getRandom(new ParentPreferences(gender, popularity));
        model.addAttribute("child", randomChildNameStats);
        String name = randomChildNameStats.getName();
        model.addAttribute("childHistory", childNameConsumer.historicalStats(name.toUpperCase())
                .orElse(new ChildNameHistoricalStats(name, Gender.fromName(name), Collections.EMPTY_MAP)).toChartData());
        model.addAttribute("choice", new ParentChoice(name));
        return "index";
    }

    @RequestMapping(value = "/{name}")
    public String name(@PathVariable String name, Model model) {
        model.addAttribute("child", childNameConsumer.lookFor(name.toUpperCase()));
        model.addAttribute("childHistory", childNameConsumer.historicalStats(name.toUpperCase())
                .orElse(new ChildNameHistoricalStats(name, Gender.fromName(name), Collections.EMPTY_MAP)).toChartData());
        model.addAttribute("choice", new ParentChoice(name.toUpperCase()));
        return "index";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        model.addAttribute("boys",  childNameConsumer.getAll(new ParentPreferences(Gender.MALE)));
        model.addAttribute("girls", childNameConsumer.getAll(new ParentPreferences(Gender.FEMALE)));
        return "all";
    }

    @RequestMapping(value = "/choice", method = RequestMethod.POST)
    public String choose(@ModelAttribute ParentChoice choice) {
        childNameConsumer.add(choice.getName().toUpperCase());
        return "redirect:/"+choice.getName().toUpperCase();
    }
}
