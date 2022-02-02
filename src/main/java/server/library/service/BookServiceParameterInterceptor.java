package server.library.service;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BookServiceParameterInterceptor {
    public static List<String> getParametersFromMethod(String methodName) throws NoSuchMethodException {
        List<String> parameters = new ArrayList<>();

        Method[] methods = BookService.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                for (Parameter p : method.getParameters()) {
                    if (p.isNamePresent()) {
                        parameters.add(p.getName());
                    }
                }
            }
        }
        log.info("Got {} from {}",parameters,methodName);
        return parameters;
    }
}
