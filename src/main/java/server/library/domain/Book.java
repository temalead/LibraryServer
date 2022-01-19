package server.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "library_id")
    private Library library;

    @ElementCollection(targetClass = Genre.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "book_genres",joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    private String name;

    private String description;

    @ElementCollection(targetClass = String.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "publishers",joinColumns = @JoinColumn(name = "book_id"))
    private Set<String> publishers;

    private Timestamp dateOfCreation;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", library=" + library.getId() +
                ", genres=" + genres +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publishers=" + publishers +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
