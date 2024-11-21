// vistas/Menu.java
package vistas;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;
import servicios.ArchivoServicio;
import utilidades.Utilidad;
import java.util.List;

public class Menu extends MenuTemplate {
    private AlumnoServicio alumnoServicio;
    private ArchivoServicio archivoServicio;

    public Menu() {
        super();
        this.alumnoServicio = new AlumnoServicio();
        this.archivoServicio = new ArchivoServicio();
    }

    @Override
    public void exportarDatos() {
        Utilidad.mostrarMensaje("--- Exportar Datos ---");
        System.out.print("Ingresa la ruta en donde se exportará el archivo: ");
        String ruta = scanner.nextLine();
        archivoServicio.exportarDatos(alumnoServicio.listarAlumnos(), ruta);
        Utilidad.mostrarMensaje("Datos exportados correctamente.");
    }

    @Override
    public void crearAlumno() {
        Utilidad.mostrarMensaje("--- Crear Alumno ---");

        String rut;
        do {
            System.out.print("Ingresa RUT (formato: 12.345.678-9): ");
            rut = scanner.nextLine();
            if (!Utilidad.validarRut(rut)) {
                System.out.println("Formato de RUT inválido. Intente nuevamente.");
            }
        } while (!Utilidad.validarRut(rut));

        if (alumnoServicio.buscarAlumnoPorRut(rut) != null) {
            System.out.println("El alumno ya existe en el sistema.");
            return;
        }

        System.out.print("Ingresa nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingresa dirección: ");
        String direccion = scanner.nextLine();

        Alumno alumno = new Alumno(rut, nombre, apellido, direccion);
        alumnoServicio.crearAlumno(alumno);

        Utilidad.mostrarMensaje("--- ¡Alumno agregado! ---");
    }

    @Override
    public void agregarMateria() {
        Utilidad.mostrarMensaje("--- Agregar Materia ---");

        System.out.print("Ingresa rut del Alumno: ");
        String rut = scanner.nextLine();

        if (alumnoServicio.buscarAlumnoPorRut(rut) == null) {
            System.out.println("Alumno no encontrado");
            return;
        }

        System.out.println("1. MATEMATICAS");
        System.out.println("2. LENGUAJE");
        System.out.println("3. CIENCIA");
        System.out.println("4. HISTORIA");
        System.out.print("Selecciona una Materia: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        MateriaEnum materiaEnum;
        switch (opcion) {
            case 1:
                materiaEnum = MateriaEnum.MATEMATICAS;
                break;
            case 2:
                materiaEnum = MateriaEnum.LENGUAJE;
                break;
            case 3:
                materiaEnum = MateriaEnum.CIENCIA;
                break;
            case 4:
                materiaEnum = MateriaEnum.HISTORIA;
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        Materia materia = new Materia(materiaEnum);
        alumnoServicio.agregarMateria(rut, materia);

        Utilidad.mostrarMensaje("--- ¡Materia agregada! ---");
    }

    @Override
    public void agregarNotaPasoUno() {
        Utilidad.mostrarMensaje("--- Agregar Nota ---");

        System.out.print("Ingresa rut del Alumno: ");
        String rut = scanner.nextLine();

        Alumno alumno = alumnoServicio.buscarAlumnoPorRut(rut);
        if (alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }

        List<Materia> materias = alumnoServicio.materiasPorAlumnos(rut);
        if (materias == null || materias.isEmpty()) {
            System.out.println("El alumno no tiene materias asignadas");
            return;
        }

        System.out.println("Alumno tiene las siguientes materias agregadas:");
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + "." + materias.get(i).getNombre());
        }

        System.out.print("Seleccionar materia: ");
        int materiaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (materiaIndex < 0 || materiaIndex >= materias.size()) {
            System.out.println("Opción no válida");
            return;
        }

        double nota;
        do {
            System.out.print("Ingresar nota (1,0 - 7,0): ");
            nota = scanner.nextDouble();
            scanner.nextLine();

            if (!Utilidad.validarNota(nota)) {
                System.out.println("La nota debe estar entre 1,0 y 7,0");
            }
        } while (!Utilidad.validarNota(nota));

        materias.get(materiaIndex).agregarNota(nota);
        Utilidad.mostrarMensaje("--- ¡Nota agregada a " + materias.get(materiaIndex).getNombre() + "! ---");
    }

    @Override
    public void listarAlumnos() {
        Utilidad.mostrarMensaje("--- Listar Alumnos ---");

        if (alumnoServicio.listarAlumnos().isEmpty()) {
            System.out.println("No hay alumnos registrados");
            return;
        }

        for (Alumno alumno : alumnoServicio.listarAlumnos().values()) {
            System.out.println("\nDatos Alumno");
            System.out.println("RUT: " + alumno.getRut());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Apellido: " + alumno.getApellido());
            System.out.println("Dirección: " + alumno.getDireccion());

            System.out.println("Materias");
            for (Materia materia : alumno.getMaterias()) {
                System.out.println(materia.getNombre());
                System.out.println("Notas:");
                System.out.println(materia.getNotas());
            }
        }
    }

    @Override
    public void terminarPrograma() {
        Utilidad.mostrarMensaje("¡Gracias por usar el sistema!");
        scanner.close();
    }
}