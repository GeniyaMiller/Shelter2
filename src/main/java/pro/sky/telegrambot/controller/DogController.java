package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Dog;
import pro.sky.telegrambot.service.DogService;

import java.util.Optional;

@RestController
@RequestMapping("dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @Operation(
            summary = "Добавляет собаку в БД.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака добавлена в БД.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Dogs"
    )
    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.addDog(dog);
    }

    @Operation(
            summary = "Обновляет информацию о собаке в БД.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака найдена в БД и обновлена информация о ней",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            },
            tags = "Dogs"
    )
    @PutMapping
    public Dog editDog(@RequestBody Dog dog) {
        return dogService.editDog(dog);
    }

    @Operation(
            summary = "Показывает информацию о собаке",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получена информация о собаке из БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Собака не найдена в БД."
                    )
            },
            tags = "Dogs"
    )

    @GetMapping("{id}")
    public Optional<Dog> getDog(@Parameter(description = "ID собаки") @PathVariable long id) {
        return dogService.getDog(id);
    }

    @Operation(
            summary = "Удаляет собаку из БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Собака удалена из БД",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Собака не найдена в БД."
                    )
            },
            tags = "Dogs"
    )
    @DeleteMapping
    public void deleteDog(@PathVariable long id) {
        dogService.deleteDog(id);
    }

}
