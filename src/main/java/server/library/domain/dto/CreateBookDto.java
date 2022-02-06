package server.library.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import server.library.domain.Genre;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
@Accessors(chain = true)
public class CreateBookDto {
    @NotNull
    private String name;
    @NotNull
    private Long library;
    @NotNull
    private Set<String> authors;

    private Set<Genre> genres;
    private String description;
    private Set<String> publishers;
    private boolean isBestseller;
    private LocalDate date_of_creation;
}
