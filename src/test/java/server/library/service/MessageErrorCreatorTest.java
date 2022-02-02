package server.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageErrorCreatorTest {

    @Test
    void makeErrorMessage() throws NoSuchMethodException {
        String request = "getBooksByParams";
        List<Optional<Object>> values = List.of(Optional.of("Bible"), Optional.of(Set.of("God")));
        String expected = "{name=Bible, authors=[God]}";

        try (MockedStatic<MessageErrorCreator> mockedStatic = Mockito.mockStatic(MessageErrorCreator.class)) {

            mockedStatic.when(() -> MessageErrorCreator.makeErrorMessage(request, values)).thenReturn("{name=Bible, authors=[God]}");
            String result = MessageErrorCreator.makeErrorMessage(request, values);

            assertEquals(expected, result);
            mockedStatic.verify(() -> BookServiceParameterInterceptor.getParametersFromMethod(request));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}