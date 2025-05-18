package mx.aula.currency.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import mx.aula.currency.models.CurrencyCode;
import mx.aula.currency.models.InfoChange;

public interface exchangerateService {
    List<CurrencyCode> parseCurrencies(String json) throws JsonMappingException, JsonProcessingException;

    // Optional<CurrencyCode> findCurrency(List<CurrencyCode> ListItems, String
    // searchItem);
    InfoChange getChange(String json) throws JsonMappingException, JsonProcessingException;
}
