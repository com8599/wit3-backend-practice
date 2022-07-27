package com.example.demo.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter     // get 함수를 아래에 정의 했을시 Getter 어노테이션은 삭제해주는것이 좋음
@Setter
@Builder    // 빌더도 마찬가지
@AllArgsConstructor
@NoArgsConstructor
// member table
public class Member {


    @JsonIgnore
    @Id
    @Column(name = "id")    // rm unnecessary annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name="email", length = 100)
    private String email;

    @Column(name="password", length = 100)
    private String password;


    @JsonIgnore
    @Column(name="activated")   // init default value
    //활성화 여부
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name")}
    )

    private Set<Authority> authorities;

    public Long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void update(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Builder
    public Member(Long id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
