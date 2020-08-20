package com.elinext.test.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "m_rooms")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "roomReservations"})
@ToString(exclude = {"roomReservations"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "roomReservation")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reservation> roomReservations = Collections.emptyList();
}
