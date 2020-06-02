package org.daistudy.springinaction5.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.daistudy.springinaction5.tacocloud.domain.Ingredient;
import org.daistudy.springinaction5.tacocloud.domain.Ingredient.Type;
import org.daistudy.springinaction5.tacocloud.domain.Design;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model){
        final List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        final Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    ingredients.stream()
                            .filter(ingredient -> Objects.equals(type, ingredient.getType()))
                            .collect(Collectors.toList()));
        }

        model.addAttribute("design", new Design());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Design design, Errors errors){
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
}
