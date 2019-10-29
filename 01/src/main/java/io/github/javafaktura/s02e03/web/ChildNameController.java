package io.github.javafaktura.s02e03.web;

import io.github.javafaktura.s02e03.core.ChildName;
import io.github.javafaktura.s02e03.core.ChildNameChooser;
import io.github.javafaktura.s02e03.core.Gender;
import io.github.javafaktura.s02e03.core.Popularity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChildNameController {

    private final ChildNameChooser chooser;

    public ChildNameController(ChildNameChooser chooser) {
        this.chooser = chooser;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("childName", chooser.getRandom(Gender.MALE, Popularity.MOST_POPULAR)
                .orElse("Couldn't get proper name!"));
        return "index";
    }

}
