package server.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.library.domain.Book;
import server.library.domain.Genre;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);


    @Query("select b from Book b join b.authors author where (:authors is null or author in(:authors))  and (:name is null or b.name=:name) " )
    List<Book> findByParams(@Param("name") String name, @Param("authors") Set<String> authors);
}
