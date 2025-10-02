package br.com.SentinelTrack.models.dto;

import br.com.SentinelTrack.models.Spot;

import java.util.UUID;

public class SpotDTO {
    private String code;
    private boolean occupied;

    public SpotDTO() {}

    public SpotDTO( String code, boolean occupied) {
        this.code = code;
        this.occupied = occupied;
    }


    public Spot toEntity() {
        Spot spot = new Spot();
        spot.setCode(this.code);
        spot.setOccupied(this.occupied);
        return spot;
    }

    public static SpotDTO fromEntity(Spot spot) {
        if (spot == null) return null;
        return new SpotDTO(spot.getCode(), spot.isOccupied());
    }
}
