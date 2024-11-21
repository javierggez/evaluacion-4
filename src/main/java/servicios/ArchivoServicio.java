package servicios;

import modelos.Alumno;
import modelos.Materia;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchivoServicio {
    private List<Alumno> alumnosACargar;
    private PromedioServicioImp promediosServicioImp;

    public ArchivoServicio() {
        this.alumnosACargar = new ArrayList<>();
        this.promediosServicioImp = new PromedioServicioImp();
    }

    public void cargarDatos(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    Alumno alumno = new Alumno(
                            datos[0].trim(),  // rut
                            datos[1].trim(),  // nombre
                            datos[2].trim(),  // apellido
                            datos[3].trim()   // direccion
                    );
                    alumnosACargar.add(alumno);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public void exportarDatos(Map<String, Alumno> alumnos, String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta + "/promedios.txt"))) {
            for (Alumno alumno : alumnos.values()) {
                writer.write(alumno.toString());
                writer.newLine();

                for (Materia materia : alumno.getMaterias()) {
                    Double promedio = promediosServicioImp.calcularPromedio(materia.getNotas());
                    writer.write("Materia: " + materia.getNombre() + " - Promedio: " + promedio);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al exportar datos: " + e.getMessage());
        }
    }
}