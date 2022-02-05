package server.library.service.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class MessageErrorCreator {

    public static String makeErrorMessage(String methodName, List<Optional<Object>> values) throws NoSuchMethodException {
        List<String> parameters = BookServiceParameterInterceptor.getParametersFromMethod(methodName);
        Map<String, String> map=new HashMap<>();
        for (int i = 0; i < parameters.size(); i++) {
            if (values.get(i).isEmpty()) {
                map.put(parameters.get(i), " is incorrect");
            } else {
                map.put(parameters.get(i), String.valueOf(values.get(i).get()));

            }
        }
        log.info("Created message with keys {} and values {}",parameters,values);
        return map.toString();
    }
}
