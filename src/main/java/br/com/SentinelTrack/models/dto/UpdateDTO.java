package br.com.SentinelTrack.models.dto;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.Update;
import br.com.SentinelTrack.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UpdateDTO{

    @NotNull
    private User user;

    @NotNull
    private Motorcycle motorcycle;

    @NotNull
    private Date date;

    @NotNull
    private String updateMessage;

    public Update toEntity(){
        return new Update(null, this.user, this.motorcycle, this.date, this.updateMessage);
    }

    public static UpdateDTO fromEntity(Update update) {
        if (update == null) return null;

        return new UpdateDTO(
                update.getUser(),
                update.getMotorcycle(),
                update.getUpdateDate(),
                update.getUpdateMessage()
                );
    }

    public static List<UpdateDTO> fromEntityList(List<Update> updates) {
        return updates.stream()
                .map(UpdateDTO::fromEntity)
                .toList();
    }
}
