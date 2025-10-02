package br.com.SentinelTrack.controllers.api;

import br.com.SentinelTrack.models.dto.UserDTO;
import br.com.SentinelTrack.models.User;
import br.com.SentinelTrack.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "Operações CRUD para usuários")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Listar todos os usuários",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso")
            }
    )
    @GetMapping
    public Page<User> getAllUsers(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.findAllPageable(pageable);
    }


    @Operation(summary = "Buscar usuário por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @GetMapping("/{id:[0-9a-fA-F\\-]{36}}")
    public UserDTO findById(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable UUID id) {
        return UserDTO.fromEntity(userService.findById(id));
    }

    @Operation(summary = "Buscar usuário por RM",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @GetMapping("/rm/{rm}")
    public UserDTO findByRm(
            @Parameter(description = "RM do usuário", required = true)
            @PathVariable String rm) {
        return UserDTO.fromEntity(userService.findByRm(rm));
    }

    @Operation(summary = "Buscar usuários por nome",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuários encontrados")
            }
    )
    @GetMapping("/{name}")
    public List<UserDTO> findByName(
            @Parameter(description = "Nome do usuário", required = true)
            @PathVariable String name) {
        return UserDTO.fromEntityList(userService.findByName(name));
    }

    @Operation(summary = "Atualizar usuário por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @PutMapping
    public UserDTO updateUser(
            @Parameter(description = "JSON do usuário", required = true)
            @RequestBody UserDTO user) {
        return UserDTO.fromEntity(userService.updateUser(user.toEntity()));
    }

    @Operation(summary = "Cadastrar novo usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
            }
    )
    @PostMapping
    public UserDTO postUser(
            @Parameter(description = "JSON de Usuario", required = true)
            @RequestBody UserDTO user) {
        User toMap = user.toEntity();
        User response = userService.postUser(toMap);
        return UserDTO.fromEntity(response);
    }

    @Operation(summary = "Deletar usuário por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteUserById(
            @Parameter(description = "ID do usuário", required = true)
            @PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @Operation(summary = "Deletar usuário por RM",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @DeleteMapping("/rm/{rm}")
    public void deleteUserByRm(
            @Parameter(description = "RM do usuário", required = true)
            @PathVariable String rm) {
        userService.deleteUserByRm(rm);
    }
}