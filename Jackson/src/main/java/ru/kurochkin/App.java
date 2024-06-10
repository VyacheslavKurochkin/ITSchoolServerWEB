package ru.kurochkin;

import ru.kurochkin.country.Country;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    private static final String COUNTRIES_FILE_NAME = "Jackson/src/main/java/ru/kurochkin/input/countries.json";
    private static final String LESS_MILLION_POPULATION_COUNTRIES_FILE_NAME = "Jackson/src/main/java/ru/kurochkin/output/resultCountries.json";
    private static final int POPULATION_LIMIT = 1000000;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<Country> countries = null;

        try {
            countries = objectMapper.readValue(new File(COUNTRIES_FILE_NAME), new TypeReference<>() {
            });
        } catch (FileNotFoundException exception) {
            System.out.println("Файл \"" + COUNTRIES_FILE_NAME + "\" не найден");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (countries != null) {
            long populationSum = countries.stream().mapToLong(country -> country.population).sum();
            System.out.println("Сумма численности по странам: " + populationSum);

            Set<Country.Currency> currenciesSet = countries.stream()
                    .flatMap(country -> country.currencies.stream())
                    .filter(currency -> currency.code != null && currency.name != null)
                    .collect(Collectors.toSet());

            System.out.println("Перечень всех валют: ");
            currenciesSet.stream().distinct()
                    .forEach(currency -> System.out.println(currency.code + " - " + currency.name));

            try {
                objectMapper.writeValue(new File(LESS_MILLION_POPULATION_COUNTRIES_FILE_NAME), countries.stream()
                        .filter(country -> country.population < POPULATION_LIMIT)
                        .collect(Collectors.toList()));
                System.out.println("Страны, где численность менее 1 млн, сохранены в файл");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}