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
@Table(name = "drinks_lookup")
public class DrinksBO {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drinks_lookup_id_generator")
    @SequenceGenerator(
            name = "drinks_lookup_id_generator",
            sequenceName = "drinks_lookup_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;
}
