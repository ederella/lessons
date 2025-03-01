package cohesion.task3;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создаем объект ObjectMapper для парсинга JSON
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = "{\"name\":\"John\", \"age\":30}";

        try {
            // Парсим JSON-строку в HashMap
            Map<String, Object> result = objectMapper.readValue(jsonString, HashMap.class);

            System.out.println("Name: " + result.get("name"));
        } catch (IOException e) {
            // Обработка ошибки парсинга
            e.printStackTrace();
        }

        try {
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println("Pretty JSON: " + prettyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
