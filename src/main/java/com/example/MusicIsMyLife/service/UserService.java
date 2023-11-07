package com.example.MusicIsMyLife.service;

import com.example.MusicIsMyLife.dto.JoinRequestDTO;
import com.example.MusicIsMyLife.dto.LoginRequestDTO;
import com.example.MusicIsMyLife.entity.UserEntity;
import com.example.MusicIsMyLife.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * userId 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    /**회원가입 기능1
     * 화면에서 JoinRequestDTO를 입력받아 UserEntity로 변환
     * userId 중복 체크는 Controller에서 진행 : 에러 메시지 출력 위해
     */
    public void join(JoinRequestDTO joinRequestDTO) {
        userRepository.save(joinRequestDTO.toEntity());
    }

    /**회원가입 기능2
     * 화면에서 JoinRequestDTO를 입력받아 UserEntity로 변환
     * 비밀번호를 암호화해서 저장
     * userId 중복 체크는 Controller에서 진행 : 에러 메시지 출력 위해
     */
    public void joinEncode(JoinRequestDTO joinRequestDTO) {
        userRepository.save(joinRequestDTO.toEntity(passwordEncoder.encode(joinRequestDTO.getUserPassword())));
    }

    /**로그인 기능
     * 화면에서 LoginRequestDTO를 입력받아 userId와 userPassword가 일치하면 UserEntity return
     * userId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public UserEntity login(LoginRequestDTO loginRequestDTO) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(loginRequestDTO.getUserId());
        System.out.println("로그인을위한확인용,,22");

        if(optionalUserEntity.isEmpty()) {
            System.out.println("로그인을위한확인용,,");
            return null;
        }

        UserEntity userEntity = optionalUserEntity.get();

        System.out.println("userEntity " + userEntity.getUserPassword());
        System.out.println("DTO " + loginRequestDTO.getUserPassword());
        System.out.println("DTO암호화 " + passwordEncoder.encode(loginRequestDTO.getUserPassword()));

        //if(!userEntity.getUserPassword().equals(passwordEncoder.encode(loginRequestDTO.getUserPassword())))
        if(!passwordEncoder.matches(loginRequestDTO.getUserPassword(), userEntity.getUserPassword())) {
            System.out.println(!passwordEncoder.matches(loginRequestDTO.getUserPassword(), userEntity.getUserPassword()));
            System.out.println("로그인을위한확인용,,33 로그인된걸까,,,");
            System.out.println("userEntity " + userEntity.getUserPassword());
            System.out.println("DTO " + passwordEncoder.encode(loginRequestDTO.getUserPassword()));
            return null;
        }
        System.out.println("userEntity " + userEntity);
        return userEntity;
    }

    /**
     * userEntityId(Long)를 입력받아 UserEntity를 return
     * 인증, 인가 시 사용
     * userEntityId가 null이거나(로그인 X) userEntityId로 찾아온 UserEntity가 없으면 null return
     * userEntityId로 찾아온 UserEntity가 존재하면 User return
     */
    /*public UserEntity getLoginUserById(Long userEntityId) {
        if(userEntityId == null) return null;

        Optional<UserEntity> optionalUserEntity = userRepository.findById(userEntityId);
        if(optionalUserEntity.isEmpty()) return null;

        return optionalUserEntity.get();
    }*/

    /**
     * userId(String)를 입력받아 UserEntity를 return
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userEntityId로 찾아온 UserEntity가 없으면 null return
     * userId로 찾아온 UserEntity가 존재하면 User return
     */
    public UserEntity getLoginUserByUserId(String userId) {
        if(userId == null) return null;

        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(userId);
        if(optionalUserEntity.isEmpty()) return null;

        return optionalUserEntity.get();
    }
}
