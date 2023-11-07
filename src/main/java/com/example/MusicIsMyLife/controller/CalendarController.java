package com.example.MusicIsMyLife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    //메인화면
    @GetMapping("/")
    public String calendar() {
        return "index";
    }
}
