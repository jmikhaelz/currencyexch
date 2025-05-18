package mx.aula.currency.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import mx.aula.currency.util.ConfigUtils;

/*
* Clase encargada de solicitud GET a la API de exchangerate.
*/
public class exchangerateClient {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String KEY_API = ConfigUtils.getProperty(".exchangerate.key");
    private HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;
    private HttpResponse<String> response;

    /*
     * getChange()  Peticion de valor de una moneda a otra
     * 
     * @param
     * currencyOfOrigin : Ingreso de la abreviatura de la moneda de origen
     * currencyOfExchange : Ingreso de la abreviatura de la moneda de destino/cambio
     * 
     * @return La respuesta de la API en formato JSON como una cadena de texto.
     * 
     * @throws IOException/InterruptedIOException Si ocurre un error al hacer la
     * solicitud.
     */
    public String getChange(String currencyOfOrigin, String currencyOfExchange)
            throws IOException, InterruptedIOException {
        URI urlString = URI.create(BASE_URL + KEY_API + "/pair/" + currencyOfOrigin + "/" + currencyOfExchange);
        try {
            // Crear solicitud de HTTP
            request = HttpRequest.newBuilder()
                    .uri(urlString)
                    .build();
            // Enviar la solicitud y obtener respuesta
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.body().contains("\"result\": \"error\"")) {
                return null;
            }
            return response.body();
        } catch (Exception e) {
            System.out.println("[SERVER] : " + e.getMessage());
        }
        return null;
    }

    /*
     * getCurrency() Peticion de monedas disponibles en la API
     *
     * @return La respuesta de la API en formato JSON como una cadena de texto.
     * 
     * @throws IOException/InterruptedIOException Si ocurre un error al hacer la
     * solicitud.
     */
    public String getCurrency() throws IOException, InterruptedIOException {
        URI uriString = URI.create(BASE_URL + KEY_API + "/codes");
        try {
            request = HttpRequest.newBuilder()
                    .uri(uriString)
                    .build();
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.body().contains("\"result\": \"error\"")) {
                return null;
            }
            return response.body();
        } catch (Exception e) {
            System.out.println("[SERVER] : " + e.getMessage());
        }
        return null;
    }
}