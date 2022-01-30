package server.library.domain;

import server.library.service.BookService;
import server.library.service.ParameterInterceptor;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(ParameterInterceptor.getParametersFromMethod(BookService.class, "getBookByParams"));
    }
}
