package ru.yegorr.ms.controller;

import org.springframework.web.bind.annotation.*;

/**
 * User: RyazantsevEV<br>
 * Date: 21.11.2021<br>
 * Time: 14:14<br>
 * Пробный контроллер
 */
@RestController
public class PingController {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/error")
    public String error() {
        return "error (";
    }
}
