package com.restapp.springrestclient.controllers;


import com.restapp.springrestclient.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, @ModelAttribute("limit") Integer limit) {

        log.debug("Received Limit value: " + limit);
        if (limit == 0) {
            log.debug("Setting limit to default of 10");
            limit = 10;
        }
        model.addAttribute("users", apiService.getUsers(limit));
        return "userlist";
    }
}
