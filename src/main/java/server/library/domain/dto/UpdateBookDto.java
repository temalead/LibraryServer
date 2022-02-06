package server.library.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import server.library.domain.Genre;

import java.time.LocalDate;
import java.util.Set;


@Accessors(chain = true)
@Setter
@Getter
@EqualsAndHashCode
public class UpdateBookDto {

    private String name;
    private Set<String> authors;
    private Set<Genre> genres;
    private String description;
    private boolean isBestseller;
    private LocalDate date_of_creation;
    private Set<String> publishers;


    @Override
    public String toString() {
        return "UpdateBookDto{" +
                "name='" + name + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", description='" + description + '\'' +
                ", isBestseller=" + isBestseller +
                ", date=" + date_of_creation +
                ", publishers=" + publishers +
                '}';
    }
}
