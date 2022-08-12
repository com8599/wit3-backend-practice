package com.example.demo.controller.dto;


import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {


    @NotNull
    private String username;

    @NotNull
    private String password;
}
