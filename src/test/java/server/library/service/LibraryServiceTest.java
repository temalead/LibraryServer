package server.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import server.library.domain.Address;
import server.library.domain.Library;
import server.library.domain.dto.CreateLibraryDto;
import server.library.repository.LibraryRepository;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    @MockBean
    private LibraryRepository repository;
    @InjectMocks
    private LibraryService service;

    @Test
    void shouldCreateNewLibrary() {
        CreateLibraryDto newLib=new CreateLibraryDto()
               .setAddress(new Address());

    }
}