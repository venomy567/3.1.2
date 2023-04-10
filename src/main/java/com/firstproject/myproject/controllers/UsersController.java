package com.firstproject.myproject.controllers;

import com.firstproject.myproject.model.User;
import com.firstproject.myproject.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UsersController {

    private final UserServices userService;

    @Autowired
    public UsersController(UserServices userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        // получить все людей из дао и передать на отображение
        model.addAttribute("users", userService.getListUser());
        return "index";
    }

    @GetMapping(value = "/{id}")
//    public String startPage(@RequestParam (value = "count", required = false, defaultValue = "5") int count, Model model) {
    public String startPage(Model model, @PathVariable("id") int id) {

        model.addAttribute("users", userService.getById(id));
        return "show";
    }

    //по этому запросу нам вернеться штмл форма в которой будут поля для ввода данных
    @GetMapping(value = "/new")
    public String newUsers (Model model) {
        // слева ключ, по которому в методе моделатрибу поймёт куда класть узера
        model.addAttribute("users", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("users") User user){
        //модел атрибут сам достанет и хттп запроса ключ и значения и положит в объект юзер который мы добавляем в бд
        userService.add(user);
        return "redirect:/";
    }

    //Через этот мапинг мы попадём на страницу где будем редактировать ползователя
    @GetMapping(value = "/{id}/edit")
    //@PathVariable находит в строке айди и пишет его в параметр айди, который передаем в метод ДАО
    public String edit (Model model, @PathVariable("id") int id) {
        //
        model.addAttribute("users", userService.getById(id));

        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
