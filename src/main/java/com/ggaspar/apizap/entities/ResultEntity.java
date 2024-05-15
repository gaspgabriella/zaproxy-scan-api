package com.ggaspar.apizap.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String url;

    @Column
    private String risk;

    @Column
    private String solution;

}
