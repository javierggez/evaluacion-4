import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import modelos.Alumno;
import modelos.Materia;
import modelos.MateriaEnum;
import servicios.AlumnoServicio;
import java.util.List;
import java.util.Map;

class AlumnoServicioTest {

    private AlumnoServicio alumnoServicio;

    @Mock
    private AlumnoServicio alumnoServicioMock;

    private Materia matematicas;
    private Materia lenguaje;
    private Alumno mapu;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        alumnoServicio = new AlumnoServicio();

        // Inicializar datos de prueba
        matematicas = new Materia(MateriaEnum.MATEMATICAS);
        lenguaje = new Materia(MateriaEnum.LENGUAJE);
        mapu = new Alumno("1.111.111-1", "Mapu", "Test", "Dirección Test");
    }

    @Test
    void crearAlumnoTest() {
        // Act
        alumnoServicio.crearAlumno(mapu);

        // Assert
        assertNotNull(alumnoServicio.buscarAlumnoPorRut("1.111.111-1"));
        assertEquals(mapu, alumnoServicio.buscarAlumnoPorRut("1.111.111-1"));
    }

    @Test
    void agregarMateriaTest() {
        // Arrange
        alumnoServicio.crearAlumno(mapu);

        // Act
        alumnoServicio.agregarMateria(mapu.getRut(), matematicas);

        // Assert
        Alumno alumnoRecuperado = alumnoServicio.buscarAlumnoPorRut(mapu.getRut());
        assertTrue(alumnoRecuperado.getMaterias().contains(matematicas));
    }

    @Test
    void materiasPorAlumnosTest() {
        
        when(alumnoServicioMock.materiasPorAlumnos("1.111.111-1"))
                .thenReturn(List.of(matematicas, lenguaje));

        
        List<Materia> materias = alumnoServicioMock.materiasPorAlumnos("1.111.111-1");

        
        assertNotNull(materias);
        assertEquals(2, materias.size());
        verify(alumnoServicioMock).materiasPorAlumnos("1.111.111-1");
    }

    @Test
    void listarAlumnosTest() {
        
        alumnoServicio.crearAlumno(mapu);

        
        Map<String, Alumno> alumnos = alumnoServicio.listarAlumnos();

        
        assertFalse(alumnos.isEmpty());
        assertEquals(1, alumnos.size());
        assertEquals(mapu, alumnos.get(mapu.getRut()));
    }

    @Test
    void agregarMateriaAlumnoNoExistenteTest() {
        
        alumnoServicio.agregarMateria("no-existe", matematicas);

        
        assertNull(alumnoServicio.buscarAlumnoPorRut("no-existe"));
    }

    @Test
    void materiasPorAlumnosAlumnoNoExistenteTest() {
        
        List<Materia> materias = alumnoServicio.materiasPorAlumnos("no-existe");

        
        assertNull(materias);
    }
}