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
@Table(name = "curries_lookup")
public class FriedRiceBO {
    @Id
    @Column(name = "id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "friedRice_lookup_id_generator")
    @SequenceGenerator(
            name = "friedRice_lookup_id_generator",
            sequenceName = "friedRice_lookup_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;
}
