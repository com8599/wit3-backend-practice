package com.example.demo.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="membertable")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {


    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name="email", length = 100)
    private String email;

    @Column(name="password", length = 100)
    private String password;


    @JsonIgnore
    @Column(name="activated")
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
