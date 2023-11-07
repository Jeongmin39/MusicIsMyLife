/*
package com.example.MusicIsMyLife.controller;

import com.example.MusicIsMyLife.dto.DiaryWriteDTO;
import com.example.MusicIsMyLife.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    //일기 작성
    @GetMapping("/diary")
    public String writeDiary() {
        return "diary";
    }

    @PostMapping("/diary")
    public String writeDiary(@ModelAttribute DiaryWriteDTO diaryWriteDTO) {
        diaryService.save(diaryWriteDTO);
        return "calendar";
    }
}


*/
