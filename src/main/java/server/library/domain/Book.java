package server.library.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@RequiredArgsConstructor
public class Book {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_id")
    private Library library;

    @ElementCollection(targetClass = Genre.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "book_genres",joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    private Timestamp issueDate;
    private Timestamp deliveryDate;

    private String description;
}
