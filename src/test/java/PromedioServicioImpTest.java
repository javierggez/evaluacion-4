import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import servicios.PromedioServicioImp;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class PromedioServicioImpTest {

    private PromedioServicioImp promedioServicio = new PromedioServicioImp();

    @Test
    void calcularPromedioTest() {
        // Caso 1: Lista con valores normales
        List<Double> notas1 = Arrays.asList(4.0, 5.0, 6.0);
        assertEquals(5.0, promedioServicio.calcularPromedio(notas1), 0.01);

        // Caso 2: Lista vacía
        List<Double> notas2 = new ArrayList<>();
        assertEquals(0.0, promedioServicio.calcularPromedio(notas2), 0.01);

        // Caso 3: Lista null
        assertEquals(0.0, promedioServicio.calcularPromedio(null), 0.01);

        // Caso 4: Lista con un solo valor
        List<Double> notas4 = Arrays.asList(5.0);
        assertEquals(5.0, promedioServicio.calcularPromedio(notas4), 0.01);

        // Caso 5: Lista con valores decimales
        List<Double> notas5 = Arrays.asList(6.5, 5.5, 4.5);
        assertEquals(5.5, promedioServicio.calcularPromedio(notas5), 0.01);
    }

    @Test
    void calcularPromedioValoresLimiteTest() {
        // Caso 1: Valores mínimos (1.0)
        List<Double> notasMinimas = Arrays.asList(1.0, 1.0, 1.0);
        assertEquals(1.0, promedioServicio.calcularPromedio(notasMinimas), 0.01);

        // Caso 2: Valores máximos (7.0)
        List<Double> notasMaximas = Arrays.asList(7.0, 7.0, 7.0);
        assertEquals(7.0, promedioServicio.calcularPromedio(notasMaximas), 0.01);

        // Caso 3: Mezcla de valores límite
        List<Double> notasMixtas = Arrays.asList(1.0, 7.0, 4.0);
        assertEquals(4.0, promedioServicio.calcularPromedio(notasMixtas), 0.01);
    }

    @Test
    void calcularPromedioValoresDecimalesTest() {
        // Caso 1: Decimales exactos
        List<Double> notas = Arrays.asList(5.5, 6.5, 4.5);
        assertEquals(5.5, promedioServicio.calcularPromedio(notas), 0.01);

        // Caso 2: Decimales periódicos
        List<Double> notasDecimales = Arrays.asList(6.6, 5.4, 4.3);
        assertEquals(5.43, promedioServicio.calcularPromedio(notasDecimales), 0.01);
    }
}