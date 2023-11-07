/*
package com.example.MusicIsMyLife.service;

import com.example.MusicIsMyLife.dto.DiaryWriteDTO;
import com.example.MusicIsMyLife.entity.DiaryEntity;
import com.example.MusicIsMyLife.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public void save(DiaryWriteDTO diaryWriteDTO) {
        DiaryEntity diaryEntity = DiaryEntity.toDiaryEntity(diaryWriteDTO);
        diaryRepository.save(diaryEntity);
    }
}

*/
