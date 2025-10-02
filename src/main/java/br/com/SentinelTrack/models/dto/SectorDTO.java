package br.com.SentinelTrack.models.dto;

import br.com.SentinelTrack.models.Sector;
import br.com.SentinelTrack.models.Spot;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class SectorDTO extends Sector{

    @NotNull
    private String name;

    @NotNull
    private List<Spot> spots;


    public SectorDTO( String name, List<Spot> spots) {
        this.name = name;
        this.spots = spots;
    }

    public Sector toEntity() {
        Sector sector = new Sector();
        sector.setName(this.name);
        return sector;
    }

    public static SectorDTO fromEntity(Sector sector) {
        if (sector == null) return null;
        return new SectorDTO(sector.getName(), sector.getSpots());
    }

    public static List<SectorDTO> fromEntityList(List<Sector> sectors) {
        return sectors.stream()
                .map(SectorDTO::fromEntity)
                .toList();
    }
}
