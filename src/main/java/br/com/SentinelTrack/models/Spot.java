package br.com.SentinelTrack.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "SPOT")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter @Setter
    private UUID id;

    @Column(nullable = false, length = 10)
    @Getter @Setter
    private String code;

    @Column(nullable = false)
    @Getter @Setter
    private boolean occupied = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    @JsonBackReference
    @Getter @Setter
    private Sector sector;

    public Spot(String code, boolean occupied) {
        this.code = code;
        this.occupied = occupied;
    }
}
