package server.library.repository;

import org.springframework.data.repository.CrudRepository;
import server.library.domain.Library;

public interface LibraryRepository extends CrudRepository<Library,Long> {
}
