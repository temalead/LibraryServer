package server.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.library.domain.Address;
import server.library.domain.Library;
import server.library.domain.dto.CreateLibraryDto;
import server.library.exception.LibraryExistByAddressException;
import server.library.repository.AddressRepository;
import server.library.repository.LibraryRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryServiceImpl implements LibraryService {
    private final LibraryRepository libraryRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public CreateLibraryDto addLibrary(CreateLibraryDto libraryDto) {
        Address address = libraryDto.getAddress();
        Optional<Address> exisingAddress = addressRepository.findAddressByCityAndStreetAndNumberOfHouse(address.getCity(),
                address.getStreet(),
                address.getNumberOfHouse());
        exisingAddress.ifPresentOrElse(s -> {
            log.error("Library with {} already exists", exisingAddress);
            throw new LibraryExistByAddressException(String.valueOf(exisingAddress));
        }, () -> {
            log.info("Oh, it`s new library in the city {}", libraryDto.getAddress().getCity());
            Library library = new Library().setAddress(address);
            libraryRepository.save(library);
        });
        return libraryDto;
    }
}
