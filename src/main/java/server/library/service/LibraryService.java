package server.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.library.domain.Address;
import server.library.domain.Library;
import server.library.domain.dto.CreateLibraryDto;
import server.library.exception.LibraryExistByAddressException;
import server.library.repository.LibraryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {
    private final LibraryRepository repository;

    public CreateLibraryDto addLibrary(CreateLibraryDto libraryDto) {
        Address address = libraryDto.getAddress();
        repository.findByAddress(address).ifPresentOrElse(s->{
            log.error("Library with {} already exists",address);
            throw new LibraryExistByAddressException(String.valueOf(address));
        }, ()->{
            Library library = new Library().setAddress(address);
            repository.save(library);
        });
        return libraryDto;
    }
}
