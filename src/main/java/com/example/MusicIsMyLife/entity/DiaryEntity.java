/*
package com.example.MusicIsMyLife.entity;

import com.example.MusicIsMyLife.dto.DiaryWriteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "diary_table")
public class DiaryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Date date;

    @Column
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    private List<DiaryImage> diaryImages;

    @Transient
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public DiaryEntity() {}

    public String getCreatedAsShort() {
        return format.format(date);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public static DiaryEntity toDiaryEntity(DiaryWriteDTO diaryWriteDTO) {
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setTitle(diaryWriteDTO.getTitle());
        diaryEntity.setDate(diaryWriteDTO.getDate());
        diaryEntity.setContent(diaryWriteDTO.getContent());
        //이미지
        return diaryEntity;
    }

}

*/
