package br.com.SentinelTrack.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "SECTOR")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter @Setter
    private UUID id;

    @NotNull
    @Column(length = 50, unique = true)
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @Getter @Setter
    private List<Spot> spots = new ArrayList<>();

    public void addSpot(Spot spot) {
        spot.setSector(this);
        this.spots.add(spot);
    }

    public void removeSpot(Spot spot) {
        this.spots.remove(spot);
        spot.setSector(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sector)) return false;
        Sector sector = (Sector) o;
        return id != null && id.equals(sector.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
