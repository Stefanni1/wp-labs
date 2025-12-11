package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) Integer rating,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("dishes", this.dishService.listDishes(rating));
        model.addAttribute("rating", rating);
        return "listDishes";
    }

    @GetMapping("/add-form")
    public String addDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", this.chefService.listChefs());
        return "dish-form";
    }

    @PostMapping
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam int rating,
                           @RequestParam(required = false) Long chefId) {
        this.dishService.create(dishId, name, cuisine, preparationTime, rating, chefId);
        return "redirect:/dishes";
    }

    @GetMapping("/edit-form/{id}")
    public String editDishPage(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        model.addAttribute("chefs", this.chefService.listChefs());
        return "dish-form";
    }

    @PostMapping("/{id}")
    public String updateDish(@PathVariable Long id,
                             @RequestParam String dishId,
                             @RequestParam String name,
                             @RequestParam String cuisine,
                             @RequestParam int preparationTime,
                             @RequestParam int rating,
                             @RequestParam(required = false) Long chefId) {
        try {
            this.dishService.update(id, dishId, name, cuisine, preparationTime, rating, chefId);
            return "redirect:/dishes";
        } catch (IllegalArgumentException ex) {
            return "redirect:/dishes?error=DishNotFound";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        this.dishService.delete(id);
        return "redirect:/dishes";
    }

    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("chefs", this.chefService.listChefs());
        return "dish-form";
    }

    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = this.dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        model.addAttribute("chefs", this.chefService.listChefs());
        return "dish-form";
    }
}

