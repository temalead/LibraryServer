package server.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.library.domain.Library;
import server.library.domain.dto.CreateLibraryDto;
import server.library.repository.LibraryRepository;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private LibraryRepository repository;

    public Library addLibrary(CreateLibraryDto libraryDto) {
        Library library = new Library();
        library.setAddress(libraryDto.getAddress());
        return library;
    }
}
