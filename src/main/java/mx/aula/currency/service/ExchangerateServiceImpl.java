package mx.aula.currency.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.aula.currency.models.CurrencyCode;
import mx.aula.currency.models.InfoChange;

public class ExchangerateServiceImpl implements exchangerateService {
    @Override
    public List<CurrencyCode> parseCurrencies(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        if (root.get("result").asText().equals("success")) {
            JsonNode codes = root.get("supported_codes");
            List<CurrencyCode> result = new ArrayList<>();
            for (JsonNode item : codes) {
                result.add(new CurrencyCode(item.get(0).asText(), item.get(1).asText()));
            }
            return result;
        }
        return null;

    }

    @Override
    // Método de búsqueda dinámica por código o nombre
    public Optional<CurrencyCode> findCurrency(List<CurrencyCode> currencies, String searchTerm) {
        return currencies.stream()
                .filter(currency -> currency.code().equalsIgnoreCase(searchTerm) ||
                        currency.name().equalsIgnoreCase(searchTerm))
                .findFirst();
    }
    /*
     * currency.ifPresentOrElse(
     * c -> System.out.println("Found: " + c),
     * () -> System.out.println("Currency not found")
     * );
     */

    @Override
    public InfoChange getChange(String json) throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        // Verificar si la respuesta fue exitosa
        if (rootNode.get("result").asText().equals("success")) {
            String baseCode = rootNode.get("base_code").asText();
            String targetCode = rootNode.get("target_code").asText();
            double conversionRate = rootNode.get("conversion_rate").asDouble();

            // Crear y retornar un objeto InfoChange con la información de la respuesta
            return new InfoChange(baseCode, targetCode, conversionRate);
        }
        return null;
    }
}