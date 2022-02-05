package server.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import server.library.service.utils.BookServiceParameterInterceptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceParameterInterceptorTest {

    @Test
    void getParametersFromMethod() throws NoSuchMethodException {
        String request = "getBooksByParams";
        List<String> list = List.of("name", "authors");


        try (MockedStatic<BookServiceParameterInterceptor> mockedStatic = Mockito.mockStatic(BookServiceParameterInterceptor.class);
        ) {
            mockedStatic.when(() -> BookServiceParameterInterceptor.getParametersFromMethod(request)).thenReturn(list);


            assertEquals(list, BookServiceParameterInterceptor.getParametersFromMethod(request));
        }
    }

}