package br.com.SentinelTrack.models.dto;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.Sector;
import br.com.SentinelTrack.models.Spot;
import br.com.SentinelTrack.models.enums.BikeType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
public class MotorcycleDTO extends Motorcycle {

    @NotNull
    @Getter @Setter
    private Sector sector;

    @NotNull
    @Getter @Setter
    private String position;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$")
    @Getter @Setter
    private String plate;

    @NotNull
    @Getter @Setter
    private BikeType type;

    @Getter @Setter
    private boolean isPlateReadable;

    public MotorcycleDTO(Sector sector, String plate, BikeType type, boolean isPlateReadable) {
        this.sector = sector;
        this.type = type;
        this.plate = plate;
        this.isPlateReadable = isPlateReadable;
    }


    public Motorcycle toEntity() {
        Motorcycle motorcycle = new Motorcycle(null, this.sector, this.plate, false, this.type);
        if (this.plate != null) motorcycle.setPlateReadable(this.isPlateReadable);
        return motorcycle;
    }

    public static MotorcycleDTO fromEntity(Motorcycle motorcycle) {
        if (motorcycle == null) return null;

        return new MotorcycleDTO(
                motorcycle.getSector(),
                motorcycle.getPlate(),
                motorcycle.getType(),
                motorcycle.isPlateReadable()
        );
    }

    public static List<MotorcycleDTO> fromEntityList(List<Motorcycle> motorcycles) {
        return motorcycles.stream()
                .map(MotorcycleDTO::fromEntity)
                .toList();
    }
}

