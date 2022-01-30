package server.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class MessageErrorCreator {

    public static String makeErrorMessage(List<String> parameters, List<Optional<Object>> values){
        Map<String, String> map=new HashMap<>();
        for (int i = 0; i < parameters.size(); i++) {
            if (values.get(i).isEmpty()) {
                map.put(parameters.get(i), " is incorrect");
            } else {
                map.put(parameters.get(i), String.valueOf(values.get(i).get()));

            }
        }
        return map.toString();
    }
}
