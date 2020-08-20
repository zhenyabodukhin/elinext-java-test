package com.elinext.test.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "m_reservations")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "userReservation", "roomReservation"})
@ToString(exclude = {"userReservation", "roomReservation"})
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String operation;

    @Column(name = "description")
    private String operationDescription;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userReservation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room roomReservation;
}
