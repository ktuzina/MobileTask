package com.training.task.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.task.pojo.Car;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PojoUtil {

    private static final String TEST_DATA_FILE = "test_cars.json";

    public static Map<String, Car> fromStringToObject() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Car> carList = new ArrayList<>();
        try {
            URL url = PojoUtil.class.getClassLoader().getResource(TEST_DATA_FILE);
            String path = Objects.requireNonNull(url).getPath();
            carList = objectMapper.readValue(new File(path), new TypeReference<List<Car>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Car> carMap = new HashMap<>();
        for (Car car : carList) {
            carMap.put(car.getTestName(), car);
        }
        return carMap;
    }

}
