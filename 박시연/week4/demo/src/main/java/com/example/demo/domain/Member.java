package com.example.demo.domain;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    private String password;

    public Long getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public void update(Long id, String email){
        this.id= id;
        this.email = email;
    }

    @Builder
    public Member(Long id, String email){
        this.id = id;
        this.email = email;
    }

}
