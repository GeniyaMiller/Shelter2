package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Owner;
import pro.sky.telegrambot.service.OwnerService;

import java.util.Optional;

@RestController
@RequestMapping("owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(
            summary = "Добавляет владельца в БД.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Владелец добавлен в БД.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Owners"
    )
    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerService.addOwner(owner);
    }

    @Operation(
            summary = "Обновляет информацию о владельце в БД.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Владелец найден в БД и обновлена информация о нем",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Owners"
    )
    @PutMapping
    public Owner editOwner(@RequestBody Owner owner) {
        return ownerService.editOwner(owner);
    }

    @Operation(
            summary = "Показывает информацию о владельце",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получена информация о владельце из БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Владелец не найден в БД."
                    )
            },
            tags = "Owners"
    )
    @GetMapping("{id}")
    public Optional<Owner> getOwner(@Parameter(description = "ID владельца") @PathVariable long id) {
        return ownerService.getOwner(id);
    }

    @Operation(
            summary = "Удаляет владельца из БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Владелец удален из БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Владелец не найден в БД."
                    )
            },
            tags = "Owners"
    )
    @DeleteMapping
    public void deleteOwner(@PathVariable long id) {
        ownerService.deleteOwner(id);
    }
}
