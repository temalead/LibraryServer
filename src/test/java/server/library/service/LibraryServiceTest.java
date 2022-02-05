package server.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import server.library.domain.Address;
import server.library.domain.Library;
import server.library.domain.dto.CreateLibraryDto;
import server.library.exception.LibraryExistByAddressException;
import server.library.repository.LibraryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {
    @Mock
    private LibraryRepository repository;
    @InjectMocks
    private LibraryService service;

    @Test
    @DisplayName("Should Create NewLibrary")
    void shouldCreateNewLibrary() {
        CreateLibraryDto newLib = new CreateLibraryDto()
                .setAddress(new Address());
        CreateLibraryDto result = service.addLibrary(newLib);
        assertEquals(newLib,result);
    }
    @Test
    @DisplayName("Should Throw LibraryExistByAddressException")
    void shouldThrowLibraryExistByAddressException(){
        Address address = new Address();
        CreateLibraryDto dto = new CreateLibraryDto()
                .setAddress(address);
        Library existingLib = new Library().setAddress(address).setId(1L);
        address.setLibrary(existingLib);
        when(repository.findByAddress(address)).thenReturn(Optional.of(existingLib));

        assertThrows(LibraryExistByAddressException.class,()->service.addLibrary(dto));
    }
}