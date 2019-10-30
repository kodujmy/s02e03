package io.github.javafaktura.s02e03.web;

import io.github.javafaktura.s02e03.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChildNameController {

    private final ChildNameChooser chooser;

    public ChildNameController(ChildNameChooser childNameChooser) {
        this.chooser = childNameChooser;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", chooser.getRandom());
        return "index";
    }
}
