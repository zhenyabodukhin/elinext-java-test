package com.elinext.test.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "m_positions")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "positionUsers"})
@ToString(exclude = {"positionUsers"})
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String positionName;

    @OneToMany(mappedBy = "userPosition")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> positionUsers = Collections.emptyList();
}
