package com.dharmendra.CineLock.catalog.show;

import com.dharmendra.CineLock.catalog.venue.Seat;
import com.dharmendra.CineLock.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.SpringVersion;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name="show_seats",
        uniqueConstraints = {
                @UniqueConstraint(name="uk_show_seat",columnNames = {"show_id","seat_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
public class ShowSeat extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ShowSeatStatus status;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "locked_until")
    private Instant lockedUntil;

    @Version
    @Column(nullable = false)
    private Long version;
}
