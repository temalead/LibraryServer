package server.library.repository;

import org.springframework.data.repository.CrudRepository;
import server.library.domain.Address;
import server.library.domain.Library;

import java.util.Optional;

public interface LibraryRepository extends CrudRepository<Library,Long> {
    Optional<Library> findByAddress(Address address);
}
