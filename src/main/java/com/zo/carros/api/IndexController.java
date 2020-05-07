package com.zo.carros.api;


import org.springframework.web.bind.annotation.*;

@RestController // transforma a classe num webservice rest
@RequestMapping("/") // essa classe vai aceitar request na página inicial
public class IndexController {

    @GetMapping
    public String hello() {
        return "Hello Spring";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("senha") String senha) {
        return "Login: " + login + "\nSenha: " + senha;
    }


}
