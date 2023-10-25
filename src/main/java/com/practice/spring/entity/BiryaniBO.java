package com.practice.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "biryani_lookup")
public class BiryaniBO {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "biryani_lookup_id_generator")
    @SequenceGenerator(
            name = "biryani_lookup_id_generator",
            sequenceName = "biryani_lookup_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;
}