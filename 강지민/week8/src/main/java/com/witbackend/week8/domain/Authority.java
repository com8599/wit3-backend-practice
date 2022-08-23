package com.witbackend.week8.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @Enumerated(EnumType.STRING)
    private Role authorityName;
}
