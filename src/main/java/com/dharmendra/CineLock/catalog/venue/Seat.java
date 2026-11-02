package com.dharmendra.CineLock.catalog.venue;

import com.dharmendra.CineLock.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="seats")
@Getter
@Setter
@NoArgsConstructor
public class Seat extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
    @Column(name = "row_label", nullable = false, length = 10)
    private String rowLabel;
    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false, length = 20)
    private SeatType seatType;
}
