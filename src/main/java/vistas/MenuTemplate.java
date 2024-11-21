package vistas;

import java.util.Scanner;

public abstract class MenuTemplate {
    protected Scanner scanner;

    public MenuTemplate() {
        this.scanner = new Scanner(System.in);
    }

    public abstract void exportarDatos();
    public abstract void crearAlumno();
    public abstract void agregarMateria();
    public abstract void agregarNotaPasoUno();
    public abstract void listarAlumnos();
    public abstract void terminarPrograma();

    public void iniciarMenu() {
        int opcion;
        do {
            System.out.println("\n1. Crear Alumnos");
            System.out.println("2. Listar Alumnos");
            System.out.println("3. Agregar Materias");
            System.out.println("4. Agregar Notas");
            System.out.println("5. Salir");
            System.out.println("6. Exportar Datos");
            System.out.print("Selección: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    listarAlumnos();
                    break;
                case 3:
                    agregarMateria();
                    break;
                case 4:
                    agregarNotaPasoUno();
                    break;
                case 5:
                    terminarPrograma();
                    return;
                case 6:
                    exportarDatos();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (true);
    }
}