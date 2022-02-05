package server.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "library_id")
    @JsonBackReference
    private Library library;

    @ElementCollection(targetClass = Genre.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "book_genres",joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    private String name;

    @ElementCollection(targetClass = String.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "authors",joinColumns = @JoinColumn(name = "book_id"))
    private Set<String> authors;

    private String description;

    @ElementCollection(targetClass = String.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "publishers",joinColumns = @JoinColumn(name = "book_id"))
    private Set<String> publishers;

    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

    private boolean isBestseller;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", library="+library.getId()+
                ", genres=" + genres +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publishers=" + publishers +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
