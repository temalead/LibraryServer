package server.library.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public enum Genre {
    CLASSIC("CLASSICAL"),
    SCI_FI("SCI_FI"),
    NOVEL("NOVEL"),
    PROSE("PROSE"),
    BIOGRAPHY("BIOGRAPHY");

    private final String genre;

    Genre(String genre) {
        this.genre=genre;
    }

    public String getGenre() {
        return genre;
    }

    private static final Map<String,Genre> existingGenres=
            Arrays.stream(Genre.values()).collect(Collectors.toMap(Genre::getGenre,genre->genre));


    public static Set<Genre> filterRequestGenres(Set<String> strings){
        return strings.stream()
                .map(String::toUpperCase)
                .map(existingGenres::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
