package br.com.SentinelTrack.models.dto;

import br.com.SentinelTrack.models.Motorcycle;
import br.com.SentinelTrack.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserDTO{

    @NotNull
    private String rm;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private List<UpdateDTO> updates;


    public User toEntity() {
        return new User(null, this.rm, this.password, this.name, this.updates.stream().map(UpdateDTO::toEntity).toList());
    }

    public static UserDTO fromEntity(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getRm(),
                user.getPassword(),
                user.getName(),
                UpdateDTO.fromEntityList(user.getUpdates())
        );
    }

    public static List<UserDTO> fromEntityList(List<User> users) {
        return users.stream()
                .map(UserDTO::fromEntity)
                .toList();
    }
}
