package com.example.MusicIsMyLife.dto;

import com.example.MusicIsMyLife.entity.UserEntity;
import com.example.MusicIsMyLife.userRole.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequestDTO {

    private Long id;

    @NotBlank(message = "사용자 이름이 비어있습니다.")
    private String userName;

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String userId;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String userPassword;
    private String userPasswordCheck;


    // 비밀번호 암호화 X
    public UserEntity toEntity() {
        return UserEntity.builder()
                .userName(this.userName)
                .userId(this.userId)
                .userPassword(this.userPassword)
                .role(UserRole.USER)
                .build();
    }

    // 비밀번호 암호화
    public UserEntity toEntity(String encodedPassword) {
        return UserEntity.builder()
                .userName(this.userName)
                .userId(this.userId)
                .userPassword(encodedPassword)
                .role(UserRole.USER)
                .build();
    }
}

