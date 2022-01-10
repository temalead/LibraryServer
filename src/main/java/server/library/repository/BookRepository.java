package server.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.library.domain.Book;

import javax.persistence.Id;
@Repository
public interface BookRepository extends JpaRepository<Book, Id> {

}
