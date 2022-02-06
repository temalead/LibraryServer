package server.library.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import server.library.domain.Genre;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;


@Accessors(chain = true)
@Setter
@Getter
//@EqualsAndHashCode - not used cause importance for hiber to equals several records
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateBookDto that = (UpdateBookDto) o;
        return isBestseller == that.isBestseller && Objects.equals(name, that.name) && Objects.equals(authors, that.authors) && Objects.equals(genres, that.genres) && Objects.equals(description, that.description) && Objects.equals(date_of_creation, that.date_of_creation) && Objects.equals(publishers, that.publishers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authors, genres, description, isBestseller, date_of_creation, publishers);
    }
}
