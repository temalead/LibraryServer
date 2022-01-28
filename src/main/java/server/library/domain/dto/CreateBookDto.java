package server.library.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import server.library.domain.Genre;

import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class CreateBookDto {
    private String name;
    private LibraryDto libraryDto;
    private Set<Genre> genres;
    private Set<String> authors;
    private String description;
    private Set<String> publishers;
    private boolean isBestseller;
    private Timestamp dateOfCreation;
}
