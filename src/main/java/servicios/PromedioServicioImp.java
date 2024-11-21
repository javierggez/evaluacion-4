package servicios;

import java.util.List;

public class PromedioServicioImp {
    public Double calcularPromedio(List<Double> valores) {
        if (valores == null || valores.isEmpty()) {
            return 0.0;
        }
        return valores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}