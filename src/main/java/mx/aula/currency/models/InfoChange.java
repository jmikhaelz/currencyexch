package mx.aula.currency.models;

public record InfoChange(String base_code, String target_code, double conversion_rate) {

    @Override
    public String toString() {
        
        return " 1 " + base_code() + " = " + Double.valueOf(conversion_rate()).toString() + " " + target_code();
    }

}
