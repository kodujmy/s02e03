package io.github.javafaktura.s02e03.web;

import io.github.javafaktura.s02e03.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChildNameController {

    private final ChildNameChooser chooser;

    public ChildNameController(ChildNameChooser childNameChooser) {
        this.chooser = childNameChooser;
    }

    @RequestMapping("/")
    public String index(@RequestParam(required = false) Gender gender,
                        @RequestParam(required = false) Popularity popularity,
                        Model model) {
        ChildName randomChildName = chooser.getRandom(new ChildNameParentPreferences(gender, popularity));
        model.addAttribute("child", randomChildName);
        model.addAttribute("choice", new ParentChoice(randomChildName.getName()));
        return "index";
    }

    @RequestMapping(value = "/{name}")
    public String name(@PathVariable String name, Model model) {
        ChildName childName = chooser.lookFor(name.toUpperCase());
        model.addAttribute("child", childName);
        model.addAttribute("choice", new ParentChoice(name.toUpperCase()));
        return "index";
    }

    @RequestMapping("/all")
    public String all(Model model) {
        model.addAttribute("boys",  chooser.getAll(new ChildNameParentPreferences(Gender.MALE)));
        model.addAttribute("girls", chooser.getAll(new ChildNameParentPreferences(Gender.FEMALE)));
        return "all";
    }

    @RequestMapping(value = "/choose", method = RequestMethod.POST)
    public String choose(@ModelAttribute ParentChoice choice) {
        chooser.add(choice.getName().toUpperCase());
        return "redirect:/all";
    }


}
