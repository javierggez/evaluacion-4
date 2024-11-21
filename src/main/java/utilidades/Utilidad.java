
package utilidades;

public class Utilidad {
    public static void limpiarPantalla() {
        try {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();
            if (sistemaOperativo.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si falla, imprimir líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static boolean validarRut(String rut) {
        return rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}\\-[\\dkK]");
    }

    public static boolean validarNota(double nota) {
        return nota >= 1.0 && nota <= 7.0;
    }
}