package com.dharmendra.CineLock.catalog.venue;

import com.dharmendra.CineLock.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="screens")
@Getter
@Setter
@NoArgsConstructor
public class Screen extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;
    @Column(nullable = false)
    private String name;
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;
}
