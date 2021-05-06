package com.restapp.springrestclient.controllers;


import com.restapp.springrestclient.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

@Controller
@Slf4j
public class UserController {

    private ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, @ModelAttribute("limit") int limit) {
//        MultiValueMap<String, String> map = serverWebExchange.getFormData().block();
//
//        Integer limit = Integer.valueOf(map.get("limit").get(0));

        log.debug("Received Limit value: " + limit);

        if (limit == 0) {
            log.debug("Setting limit to default of 10");
            limit = 10;
        }
        model.addAttribute("users", apiService.getUsers(limit));
        return "userlist";
    }
}
