package com.lm99.sandwichservices.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_id_sequence",
            sequenceName = "role_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_sequence"
    )
    private Long id;

    @Column(
            unique = true
    )
    private String name;

    private String description;

}
