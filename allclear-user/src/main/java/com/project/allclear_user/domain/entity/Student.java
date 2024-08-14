package com.project.allclear_user.domain.entity;

import com.project.allclear_user.domain.dto.ProfileDto;
import com.project.allclear_user.domain.dto.SignupDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    private String loginId;
    private String password;
    private String studentIdNumber;
    private int grade;
    private String refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.studentIdNumber;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateProfile(ProfileDto requestDto, String encodedPw) {
        this.loginId = requestDto.getLoginId();
        this.studentIdNumber = requestDto.getStudentIdNumber();
        this.grade = requestDto.getGrade();
        this.password = encodedPw;
    }
}
