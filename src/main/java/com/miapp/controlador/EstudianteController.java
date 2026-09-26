package com.miapp.controlador;

import com.miapp.modelo.Curso;
import com.miapp.modelo.Estudiante;
import com.miapp.modelo.Profesor;
import com.miapp.servicios.IBuscador;
import com.miapp.vista.EstudianteView;

import java.util.ArrayList;
import java.util.List;

public class EstudianteController implements IBuscador {

    // ── Constantes finales ────────────────────────────────────────────────────
    private static final int CANTIDAD_ESTUDIANTES_INICIALES = 12;
    private static final String MENSAJE_BUSQUEDA_VACIA = "Por favor ingrese un nombre para buscar.";
    private static final String MENSAJE_BUSQUEDA_CARRERA_VACIA = "Por favor seleccione una carrera para buscar.";
    private static final String MENSAJE_SIN_RESULTADOS = "No se encontraron estudiantes con ese criterio.";

    // ── Vista ─────────────────────────────────────────────────────────────────
    private EstudianteView vista;

    // ── Array de estudiantes (fuente de datos) ────────────────────────────────
    private Estudiante[] estudiantes;
    private List<Profesor> listaProfesores;

    // ── Constructor ───────────────────────────────────────────────────────────

    public EstudianteController(EstudianteView vista) {
        this.vista = vista;
        this.listaProfesores = new ArrayList<>();
        // Primero cargar datos (inicializar estudiantes[])
        cargarDatos();
        // Luego asignar controlador a la vista (ahora es seguro acceder a estudiantes[])
        this.vista.setControlador(this);
    }

    // ── Implementación de la interfaz IBuscador ───────────────────────────────

    @Override
    public void cargarDatos() {
        inicializarEstudiantes();
    }

    @Override
    public void buscarEstudiante(String criterio) {
        buscarPorCriterio(criterio);
    }

    @Override
    public void buscarEstudiantePorCarrera(String carrera) {
        buscarPorCarrera(carrera);
    }

    @Override
    public void inscribirCurso(Curso curso) {
        if (curso == null) {
            return;
        }
        System.out.println("Procesando inscripción del curso: " + curso.getCodigo());
    }
    
    // ── Carga de datos iniciales ──────────────────────────────────────────────

    private void inicializarEstudiantes() {
        estudiantes = new Estudiante[CANTIDAD_ESTUDIANTES_INICIALES];

        // Reinicia el contador estático de Estudiante antes de cargar nuevos datos
        Estudiante.reiniciarContador();

        estudiantes[0]  = new Estudiante(1,  "Ana ","García",        "Ingeniería de Sistemas",  4.5);
        estudiantes[1]  = new Estudiante(2,  "Carlos"," López",      "Ingeniería Civil",        3.8);
        estudiantes[2]  = new Estudiante(3,  "María", "Rodríguez",   "Medicina",                4.9);
        estudiantes[3]  = new Estudiante(4,  "José ","Martínez",     "Derecho",                 3.5);
        estudiantes[4]  = new Estudiante(5,  "Laura ","Sánchez",     "Administración",          4.1);
        estudiantes[5]  = new Estudiante(6,  "Andrés ","Torres",     "Ingeniería de Sistemas",  3.9);
        estudiantes[6]  = new Estudiante(7,  "Valentina ","Gómez",   "Psicología",              4.3);
        estudiantes[7]  = new Estudiante(8,  "Luis ","Herrera",      "Economía",                3.7);
        estudiantes[8]  = new Estudiante(9,  "Sofía ","Díaz",        "Ingeniería Civil",        4.6);
        estudiantes[9]  = new Estudiante(10, "Juliana ","Morales",   "Medicina",                4.8);
        estudiantes[10] = new Estudiante(11, "Ana Milena ","Ruiz",   "Derecho",                 4.0);
        estudiantes[11] = new Estudiante(12, "Carlos Andrés ","Paz", "Administración",          3.6);

        // Log: informa cuántos estudiantes se cargaron usando static getTotalEstudiantes()
        System.out.println("Total de estudiantes cargados: " + Estudiante.getTotalEstudiantes());
    }

    // ── Lógica de búsqueda ────────────────────────────────────────────────────

  
    private void buscarPorCriterio(String criterio) {

        // Validación básica usando constante final
        if (criterio == null || criterio.trim().isEmpty()) {
            if (vista != null) vista.mostrarError(MENSAJE_BUSQUEDA_VACIA);
            return;
        }

        List<Estudiante> resultados = new ArrayList<>();
        String criterioBajo = criterio.toLowerCase();

        for (Estudiante e : estudiantes) {
            // Validar que el elemento no sea null
            if (e != null && (e.getNombre().toLowerCase().contains(criterioBajo) ||
                e.getApellido().toLowerCase().contains(criterioBajo))) {
                resultados.add(e);
            }
        }

        if (resultados.isEmpty()) {
            vista.mostrarEstudiantes(new ArrayList<>()); // mostrará mensaje vacío
        } else if (resultados.size() == 1) {
            // Un solo resultado: usar vista.mostrarEstudiante(fila)
            vista.mostrarEstudiante(convertirAFila(resultados.get(0)));
        } else {
            // Varios resultados: mostrar lista completa ya convertida a filas
            vista.mostrarEstudiantes(convertirAFilas(resultados));
        }
    }

   
    private void buscarPorCarrera(String carrera) {
        // Validación básica usando constante final
        if (carrera == null || carrera.isEmpty() || carrera.equals("Seleccionar...")) {
            vista.mostrarError(MENSAJE_BUSQUEDA_CARRERA_VACIA);
            return;
        }

        List<Estudiante> resultados = new ArrayList<>();

        // Búsqueda exacta por carrera
        for (Estudiante e : estudiantes) {
            // Validar que el elemento no sea null
            if (e != null && e.getCarrera().equalsIgnoreCase(carrera)) {
                resultados.add(e);
            }
        }

        // Mostrar resultados (ya convertidos a filas, no como Estudiante)
        vista.mostrarEstudiantes(convertirAFilas(resultados));
    }

    
    private Object[] convertirAFila(Estudiante e) {
        return new Object[]{
            e.getId(),
            e.getNombre(),
            e.getApellido(),
            e.getCarrera(),
            String.format("%.2f", e.getPromedio())
        };
    }
  
    private List<Object[]> convertirAFilas(List<Estudiante> lista) {
        List<Object[]> filas = new ArrayList<>();
        for (Estudiante e : lista) {
            filas.add(convertirAFila(e));
        }
        return filas;
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        for (Estudiante e : estudiantes) {
            if (e != null && e.getId() == id) {
                return e;
            }
        }
        return null;
    }
    
    public String[] obtenerListaProfesores() {
        return new String[] {
            "Seleccionar...",
            "Dr. Carlos Mendoza",
            "Ing. Ana María Gómez",
            "Lic. Roberto Silva"
        };
    }
    
    public String[] obtenerCarrerasUnicas() {
        List<String> carreras = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            // Validar que el elemento no sea null
            if (e != null) {
                String carrera = e.getCarrera();
                if (!carreras.contains(carrera)) {
                    carreras.add(carrera);
                }
            }
        }
        return carreras.toArray(new String[0]);
    }

 
    public final int obtenerTotalEstudiantes() {
        return Estudiante.getTotalEstudiantes();
    }
   
    public boolean agregarEstudiante(String nombre, String apellido, String carrera, double promedio) {
        // Validación de datos
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() ||
            carrera == null || carrera.isEmpty()) {
            vista.mostrarError("Todos los campos son obligatorios.");
            return false;
        }

        // Expandir el array si es necesario antes de agregar
        if (estudiantes.length == Estudiante.getTotalEstudiantes()) {
            // El array está lleno, crear uno más grande
            Estudiante[] nuevoArray = new Estudiante[estudiantes.length + 5];
            System.arraycopy(estudiantes, 0, nuevoArray, 0, estudiantes.length);
            estudiantes = nuevoArray;
        }

        // Obtener el índice donde se guardará el nuevo estudiante
        int indiceNuevoEstudiante = Estudiante.getTotalEstudiantes();

        // Crear nuevo estudiante con ID automático basado en el contador static
        int proximoId = Estudiante.getProximoId();
        Estudiante nuevoEstudiante = new Estudiante(proximoId, nombre, apellido, carrera, promedio);

        // Agregar el nuevo estudiante en la posición correcta
        estudiantes[indiceNuevoEstudiante] = nuevoEstudiante;

        // Mostrar mensaje de éxito
        vista.mostrarMensaje("Estudiante agregado correctamente.\nTotal de estudiantes: " +
                            Estudiante.getTotalEstudiantes());

        return true;
    } 
    
    public void buscarEstudiantesPorCurso(String codigoCurso) {
        if (codigoCurso == null || codigoCurso.isEmpty()) {
            vista.mostrarError("Seleccione un curso válido.");
            return;
        }

        List<Estudiante> resultados = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (e != null && e.getCursosInscritos() != null && e.getCursosInscritos().contains(codigoCurso)) {
                resultados.add(e);
            }
        }

        String nombreProfesor = "Ninguno";
        for (Profesor p : listaProfesores) {
            if (p != null && p.getCursosAsignados() != null && p.getCursosAsignados().contains(codigoCurso)) {
                nombreProfesor = p.getNombre();
                break;
            }
        }

        vista.setProfesorAsignado(nombreProfesor);
        vista.mostrarEstudiantes(convertirAFilas(resultados));
    }

    public void inscribirEstudianteEnCurso(int idEstudiante, String codigoCurso) {
        Estudiante e = obtenerEstudiantePorId(idEstudiante);
        if (e == null) {
            vista.mostrarError("El estudiante seleccionado no fue encontrado.");
            return;
        }

        if (e.getCursosInscritos() == null) {
            e.setCursosInscritos(new ArrayList<>());
        }

        if (e.getCursosInscritos().contains(codigoCurso)) {
            vista.mostrarError("El estudiante ya se encuentra inscrito en " + codigoCurso + ".");
            return;
        }

        e.getCursosInscritos().add(codigoCurso);
        vista.mostrarMensaje("Estudiante " + e.getNombre().trim() + " " + e.getApellido().trim() +
                            " inscrito exitosamente en el curso " + codigoCurso + ".");
    }

    public boolean agregarProfesor(String nombre, double salarioBase) {
        if (nombre == null || nombre.trim().isEmpty()) {
            vista.mostrarError("El nombre del profesor no puede estar vacío.");
            return false;
        }

        for (Profesor p : listaProfesores) {
            if (p != null && p.getNombre().equalsIgnoreCase(nombre.trim())) {
                vista.mostrarError("Ya existe un profesor registrado con ese nombre.");
                return false;
            }
        }

        listaProfesores.add(new Profesor(nombre.trim(), salarioBase));
        vista.mostrarMensaje("Profesor '" + nombre + "' registrado con éxito con un salario de $" + salarioBase);
        return true;
    }

    public void verCursosDeProfesor(String nombreProfesor) {
        for (Profesor p : listaProfesores) {
            if (p != null && p.getNombre().equals(nombreProfesor)) {
                if (p.getCursosAsignados() == null || p.getCursosAsignados().isEmpty()) {
                    vista.mostrarMensaje("El profesor " + nombreProfesor + " no tiene cursos asignados.");
                } else {
                    String cursos = String.join(", ", p.getCursosAsignados());
                    vista.mostrarMensaje("Cursos a cargo de " + nombreProfesor + ": [" + cursos + "]");
                }
                return;
            }
        }
        vista.mostrarError("No se encontró el profesor seleccionado.");
    }

    public void asignarProfesorACurso(String nombreProfesor, String codigoCurso) {
        Profesor profEncontrado = null;
        for (Profesor p : listaProfesores) {
            if (p != null && p.getNombre().equals(nombreProfesor)) {
                profEncontrado = p;
                break;
            }
        }

        if (profEncontrado == null) {
            vista.mostrarError("El profesor seleccionado no existe.");
            return;
        }

        if (profEncontrado.getCursosAsignados() == null) {
            profEncontrado.setCursosAsignados(new ArrayList<>());
        }

        if (!profEncontrado.getCursosAsignados().contains(codigoCurso)) {
            profEncontrado.getCursosAsignados().add(codigoCurso);
        }

        vista.setProfesorAsignado(profEncontrado.getNombre());
        vista.mostrarMensaje("Se ha asignado al profesor " + nombreProfesor + " el curso " + codigoCurso + ".");
    }

    public void buscarEstudiantesPorEstado(String estado) {
        List<Estudiante> resultados = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            if (e != null && e.getEstadoMatricula() != null && e.getEstadoMatricula().equalsIgnoreCase(estado)) {
                resultados.add(e);
            }
        }
        vista.mostrarEstudiantes(convertirAFilas(resultados));
    }

    public void cambiarEstadoMatricula(int idEstudiante, String nuevoEstado) {
        Estudiante e = obtenerEstudiantePorId(idEstudiante);
        if (e == null) {
            vista.mostrarError("Seleccione un estudiante válido de la tabla.");
            return;
        }

        e.setEstadoMatricula(nuevoEstado);
        vista.mostrarMensaje("Estado de matrícula actualizado a '" + nuevoEstado + "' para " +
                             e.getNombre().trim() + " " + e.getApellido().trim() + ".");
    }
}

}