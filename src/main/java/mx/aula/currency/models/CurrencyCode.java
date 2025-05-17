package mx.aula.currency.models;

public record CurrencyCode(String code, String name) {

    @Override
    public String toString() {
        return code() + " - " + name();
    }
}
