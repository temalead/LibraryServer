package server.library.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import server.library.domain.Genre;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
public class CreateBookDto {
    @NotNull
    private String name;
    @NotNull
    private FindLibraryDto findLibraryDto;
    private Set<Genre> genres;
    @NotNull
    private Set<String> authors;
    private String description;
    private Set<String> publishers;
    private boolean isBestseller;
    private Timestamp dateOfCreation;
}
