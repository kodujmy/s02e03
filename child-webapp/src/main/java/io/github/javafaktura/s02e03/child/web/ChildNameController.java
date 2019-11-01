package io.github.javafaktura.s02e03.child.web;

import io.github.javafaktura.s02e03.child.core.model.*;
import io.github.javafaktura.s02e03.child.core.service.ChildNameMemoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChildNameController {

    private final ChildNameMemoryService chooser;

    public ChildNameController(ChildNameMemoryService childNameChooser) {
        this.chooser = childNameChooser;
    }

    @RequestMapping("/")
    public String index(@RequestParam(required = false) Gender gender,
                        @RequestParam(required = false) Popularity popularity,
                        Model model) {
        ChildNameStats randomChildNameStats = chooser.getRandom(new ParentPreferences(gender, popularity));
        model.addAttribute("child", randomChildNameStats);
        model.addAttribute("choice", new ParentChoice(randomChildNameStats.getName()));
        return "index";
    }

    @RequestMapping(value = "/{name}")
    public String name(@PathVariable String name, Model model) {
        ChildNameStats childNameStats = chooser.lookFor(name.toUpperCase());
        model.addAttribute("child", childNameStats);
        model.addAttribute("choice", new ParentChoice(name.toUpperCase()));
        return "index";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        model.addAttribute("boys",  chooser.getAll(new ParentPreferences(Gender.MALE)));
        model.addAttribute("girls", chooser.getAll(new ParentPreferences(Gender.FEMALE)));
        return "all";
    }

    @RequestMapping(value = "/choose", method = RequestMethod.POST)
    public String choose(@ModelAttribute ParentChoice choice) {
        chooser.add(choice.getName().toUpperCase());
        return "redirect:/all";
    }


}
