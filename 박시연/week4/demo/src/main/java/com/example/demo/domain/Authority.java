package com.example.demo.domain;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)   // name 을 지웠을때 스네이크방식으로 안될 경우 PhysicalNamingStrategyImpl를
                                                    // 재정의 하여 스네이크로 받을 수 있도록 할 수 있음
    private String authorityName;
}
