package server.library.domain;

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
}
