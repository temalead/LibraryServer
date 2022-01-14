package server.library.domain;


import com.google.gson.Gson;

import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        book.setDescription("ASFDasdfasdf");
        book.setGenres(Set.of(Genre.CLASSIC));
        book.setId(1L);
        book.setLibrary(new Library());
        String json = new Gson().toJson(book);
        System.out.println(json);
    }
}
