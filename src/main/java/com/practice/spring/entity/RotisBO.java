package com.practice.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rotis_lookup")
public class RotisBO {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "starters_lookup_id_generator")
    @SequenceGenerator(
            name = "starters_lookup_id_generator",
            sequenceName = "starters_lookup_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;
}
