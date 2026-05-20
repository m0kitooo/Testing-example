package com.learning.courses.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_number_id_seq")
    @SequenceGenerator(name = "phone_number_id_seq", sequenceName = "phone_number_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
