package server.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.library.domain.Library;
import server.library.domain.dto.CreateLibraryDto;
import server.library.service.LibraryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService service;

    @PostMapping("/new")
    public ResponseEntity<CreateLibraryDto> addLibrary(@RequestBody @Validated CreateLibraryDto libraryDto){
        return new ResponseEntity<>(service.addLibrary(libraryDto), HttpStatus.CREATED);
    }
}
