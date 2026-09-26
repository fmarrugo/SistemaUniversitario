package com.miapp.vista;

import com.miapp.controlador.EstudianteController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EstudianteView extends JFrame {

    // ── Constantes finales para dimensiones ────────────────────────────────────
    private static final int ANCHO_VENTANA = 1000;
    private static final int ALTO_VENTANA = 700;
    private static final int ANCHO_CAMPO_BUSQUEDA = 18;
    private static final int ANCHO_CAMPO_AGREGAR = 12;
    private static final int ALTO_FILA_TABLA = 24;

    // ── Constantes finales para textos ─────────────────────────────────────────
    private static final String TITULO_VENTANA = "Gestión de Estudiantes — MVC (Búsqueda + Agregar + Cursos + Profesores)";
    private static final String TITULO_PANEL_BUSQUEDA = "Buscar estudiante por nombre";
    private static final String TITULO_PANEL_CARRERA = "Buscar por carrera";
    private static final String TITULO_PANEL_AGREGAR = "Agregar nuevo estudiante";
    private static final String TITULO_PANEL_CURSOS = "Cursos: Inscripción y consulta";
    private static final String TITULO_PANEL_PROFESORES = "Profesores: agregar y asignar a curso";
    private static final String TITULO_PANEL_ESTADO = "Estado de matrícula: buscar y cambiar";
    private static final String TITULO_PANEL_RESULTADOS = "Resultados";
    
    private static final String LABEL_CURSO = "Curso:";
    private static final String LABEL_NOMBRE = "Nombre:";
    private static final String LABEL_APELLIDO = "Apellido:";
    private static final String LABEL_CARRERA = "Carrera:";
    private static final String LABEL_PROMEDIO = "Promedio:";
    private static final String LABEL_SALARIO = "Salario base:";
    private static final String LABEL_PROFESOR = "Profesor:";
    private static final String LABEL_ESTADO = "Nuevo estado:";
    
    private static final String BOTON_BUSCAR = "Buscar";
    private static final String BOTON_BUSCAR_CARRERA = "Buscar por Carrera";
    private static final String BOTON_LIMPIAR = "Limpiar";
    private static final String BOTON_AGREGAR = "Agregar Estudiante";
    private static final String BOTON_VER_ESTUDIANTES = "Ver estudiantes del curso";
    private static final String BOTON_INSCRIBIR_CURSO = "Inscribir en curso";
    private static final String BOTON_AGREGAR_PROFESOR = "Agregar Profesor";
    private static final String BOTON_VER_CURSOS_PROF = "Ver cursos del profesor";
    private static final String BOTON_ASIGNAR_CURSO = "Asignar a curso";
    private static final String BOTON_BUSCAR_ESTADO = "Buscar por estado";
    private static final String BOTON_CAMBIAR_ESTADO = "Cambiar estado";
    
    private static final String OPCION_SELECCIONAR = "Seleccionar...";
    private static final String MENSAJE_INICIAL = "Ingrese un nombre o seleccione una carrera y presione Buscar.";
    private static final String MENSAJE_ENCONTRADO_UNO = "Se encontró 1 estudiante.";
    private static final String MENSAJE_ENCONTRADOS_VARIOS = "Se encontraron {0} estudiante(s).";
    private static final String MENSAJE_SIN_RESULTADOS = "No se encontraron estudiantes con ese criterio.";

    // ── Constantes finales para colores ────────────────────────────────────────
    private static final Color COLOR_BOTON_FONDO = new Color(59, 139, 212);
    private static final Color COLOR_BOTON_CARRERA = new Color(76, 175, 80);
    private static final Color COLOR_BOTON_LIMPIAR = new Color(244, 67, 54);
    private static final Color COLOR_BOTON_AGREGAR = new Color(103, 58, 183);
    private static final Color COLOR_BOTON_TEAL = new Color(0, 150, 136);
    private static final Color COLOR_BOTON_NARANJA = new Color(240, 173, 78);
    private static final Color COLOR_BOTON_INDIGO = new Color(63, 81, 181);
    private static final Color COLOR_BOTON_TEXTO = Color.WHITE;
    private static final Color COLOR_ESTADO_TEXTO = Color.GRAY;

    // ── Columnas de la tabla (constante final) ─────────────────────────────────
    private static final String[] COLUMNAS_TABLA = {"ID", "Nombre", "Apellido", "Carrera", "Promedio"};
    private static final int INDICE_PROMEDIO = 4;
    
    private static final String[] CURSOS_DEFAULT = {"BDA150", "SIS101", "FIS201"};
    private static final String[] ESTADOS_MATRICULA = {OPCION_SELECCIONAR, "Matriculado", "Inactivo", "Graduado"};
    private static final String[] PROFESORES_DEFAULT = {
        "Seleccionar...",
        "Dr. Isaias Mendoza",
        "Ing. Ana María Gómez",
        "Lic. Juana Silva"
    };

    // ── Componentes UI - Búsqueda por nombre ────────────────────────────────────
    private JTextField             txtNombre;
    private JButton                btnBuscar;

    // ── Componentes UI - Búsqueda por carrera ──────────────────────────────────
    private JComboBox<String>      cmbCarrera;
    private JButton                btnBuscarCarrera;
    private JButton                btnLimpiar;

    // ── Componentes UI - Agregar estudiante ────────────────────────────────────
    private JTextField             txtAgregarNombre;
    private JTextField             txtAgregarApellido;
    private JComboBox<String>      cmbAgregarCarrera;
    private JSpinner               spinPromedio;
    private JButton                btnAgregar;

    // -- Componentes UI - Cursos
    private JComboBox<String> comboCursos;
    private JButton btnVerEstudiantesCurso;
    private JButton btnInscribirCurso;
    private JLabel lblProfesorAsignado;
    
    // ── Componentes UI - Profesores ────────────────────────────────────────────
    private JTextField               txtNombreProfesor;
    private JSpinner                 spinnerSalarioBase;
    private JButton                  btnAgregarProfesor;
    private JComboBox<String>        comboProfesores;
    private JButton                  btnVerCursosProfesor;
    private JComboBox<String>        comboCursosAsignar;
    private JButton                  btnAsignarCurso;

    // ── Componentes UI - Estado de Matrícula ────────────────────────────────────
    private JComboBox<String>        comboEstadoMatricula;
    private JButton                  btnBuscarPorEstado;
    private JButton                  btnCambiarEstado;

    // ── Componentes UI - Resultados y Estado ────────────────────────────────────
    private JTable                 tblResultados;
    private DefaultTableModel      modeloTabla;
    private JLabel                 lblEstado;
    private JLabel                 lblTotalEstudiantes;
    

    // ── Controlador ───────────────────────────────────────────────────────────
    private EstudianteController controlador;

    // ── Constructor ───────────────────────────────────────────────────────────

    public EstudianteView() {
        initComponentes();
        initEventos();
    }

    // ── Inicialización de componentes ─────────────────────────────────────────

   
    private void initComponentes() {
        setTitle(TITULO_VENTANA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ────────────────────────────────────────────────────────────────────────
        // PANEL SUPERIOR: Búsqueda y Agregar (con GridLayout)
        // ────────────────────────────────────────────────────────────────────────
        
        //Aqui ajusto el panel para que todo quede ajustado en vertical
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        
        // Panel búsqueda por nombre (Fila 1)
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_BUSQUEDA));

        JLabel lblNombre = new JLabel(LABEL_NOMBRE);
        txtNombre = new JTextField(ANCHO_CAMPO_BUSQUEDA);
        btnBuscar = new JButton(BOTON_BUSCAR);
        btnBuscar.setBackground(COLOR_BOTON_FONDO);
        btnBuscar.setForeground(COLOR_BOTON_TEXTO);
        btnBuscar.setFocusPainted(false);

        panelBusqueda.add(lblNombre);
        panelBusqueda.add(txtNombre);
        panelBusqueda.add(btnBuscar);

        // Panel búsqueda por carrera (Fila 2)
        JPanel panelCarrera = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelCarrera.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_CARRERA));

        JLabel lblCarrera = new JLabel(LABEL_CARRERA);
        cmbCarrera = new JComboBox<>();
        cmbCarrera.addItem(OPCION_SELECCIONAR);
        // Se carga después, cuando el controlador esté disponible

        btnBuscarCarrera = new JButton(BOTON_BUSCAR_CARRERA);
        btnBuscarCarrera.setBackground(COLOR_BOTON_CARRERA);
        btnBuscarCarrera.setForeground(COLOR_BOTON_TEXTO);
        btnBuscarCarrera.setFocusPainted(false);

        btnLimpiar = new JButton(BOTON_LIMPIAR);
        btnLimpiar.setBackground(COLOR_BOTON_LIMPIAR);
        btnLimpiar.setForeground(COLOR_BOTON_TEXTO);
        btnLimpiar.setFocusPainted(false);

        panelCarrera.add(lblCarrera);
        panelCarrera.add(cmbCarrera);
        panelCarrera.add(btnBuscarCarrera);
        panelCarrera.add(btnLimpiar);

        // Panel agregar estudiante (Fila 3)
        JPanel panelAgregar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelAgregar.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_AGREGAR));

        JLabel lblAgregarNombre = new JLabel(LABEL_NOMBRE);
        txtAgregarNombre = new JTextField(ANCHO_CAMPO_AGREGAR);

        JLabel lblAgregarApellido = new JLabel(LABEL_APELLIDO);
        txtAgregarApellido = new JTextField(ANCHO_CAMPO_AGREGAR);

        JLabel lblAgregarCarrera = new JLabel(LABEL_CARRERA);
        cmbAgregarCarrera = new JComboBox<>();
        cmbAgregarCarrera.addItem(OPCION_SELECCIONAR);
        // Se carga después, cuando el controlador esté disponible

        JLabel lblAgregarPromedio = new JLabel(LABEL_PROMEDIO);
        spinPromedio = new JSpinner(new SpinnerNumberModel(3.0, 0.0, 5.0, 0.1));
        spinPromedio.setPreferredSize(new Dimension(60, 25));

        btnAgregar = new JButton(BOTON_AGREGAR);
        btnAgregar.setBackground(COLOR_BOTON_AGREGAR);
        btnAgregar.setForeground(COLOR_BOTON_TEXTO);
        btnAgregar.setFocusPainted(false);

        panelAgregar.add(lblAgregarNombre);
        panelAgregar.add(txtAgregarNombre);
        panelAgregar.add(lblAgregarApellido);
        panelAgregar.add(txtAgregarApellido);
        panelAgregar.add(lblAgregarCarrera);
        panelAgregar.add(cmbAgregarCarrera);
        panelAgregar.add(lblAgregarPromedio);
        panelAgregar.add(spinPromedio);
        panelAgregar.add(btnAgregar);
        
        //Panel cursos
        JPanel panelCursos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelCursos.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_CURSOS));
 
        JLabel lblCurso = new JLabel(LABEL_CURSO);
        comboCursos = new JComboBox<>(CURSOS_DEFAULT);
        btnVerEstudiantesCurso = new JButton(BOTON_VER_ESTUDIANTES);
        btnInscribirCurso = new JButton(BOTON_INSCRIBIR_CURSO);
        JLabel lblInfo = new JLabel("Primero busque y seleccione un estudiante en la tabla");
        lblInfo.setForeground(Color.GRAY);
        JLabel lblProfeAsignado = new JLabel("Profesor Asignado (ninguno)");
        lblProfeAsignado.setForeground(Color.BLUE);
        lblProfeAsignado.setFont(lblProfeAsignado.getFont().deriveFont(Font.BOLD));
        
        panelCursos.add(lblCurso);
        panelCursos.add(comboCursos);
        panelCursos.add(btnVerEstudiantesCurso);
        panelCursos.add(btnInscribirCurso);
        panelCursos.add(lblInfo);
        panelCursos.add(lblProfeAsignado);
        
        //Panel Profesores
        JPanel panelProfesores = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelProfesores.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_PROFESORES));
        
        JLabel lblProfesor = new JLabel(LABEL_NOMBRE);
        txtNombreProfesor = new JTextField(8);
        JLabel lblSalario = new JLabel(LABEL_SALARIO);
        spinnerSalarioBase = new JSpinner(new SpinnerNumberModel(3000000, 1000000, 20000000, 500000));
        btnAgregarProfesor = new JButton(BOTON_AGREGAR_PROFESOR);
        JLabel lblProfesor1 = new JLabel(LABEL_PROFESOR);
        comboProfesores = new JComboBox<>(PROFESORES_DEFAULT);
        btnVerCursosProfesor = new JButton(BOTON_VER_CURSOS_PROF);
        JLabel lblCursoAsignar = new JLabel("Curso a Asignar");
        comboCursosAsignar = new JComboBox<>(CURSOS_DEFAULT);
        btnAsignarCurso = new JButton(BOTON_ASIGNAR_CURSO);
        
        panelProfesores.add(lblProfesor);
        panelProfesores.add(txtNombreProfesor);
        panelProfesores.add(lblSalario);
        panelProfesores.add(spinnerSalarioBase);
        panelProfesores.add(btnAgregarProfesor);
        panelProfesores.add(lblProfesor1);
        panelProfesores.add(comboProfesores);
        panelProfesores.add(btnVerCursosProfesor);
        panelProfesores.add(lblCursoAsignar);
        panelProfesores.add(comboCursosAsignar);
        panelProfesores.add(btnAsignarCurso);
        
        //Panel Estado
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelEstado.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_ESTADO));
        
        JLabel lblEstado = new JLabel(LABEL_ESTADO);
        comboEstadoMatricula = new JComboBox<>(ESTADOS_MATRICULA);
        btnBuscarPorEstado = new JButton(BOTON_BUSCAR_ESTADO);
        btnCambiarEstado = new JButton(BOTON_CAMBIAR_ESTADO);
        JLabel lblInfoEstado = new JLabel("(\"Cambiar estado\" requiere seleccionar un estudiante)");
        lblInfoEstado.setForeground(Color.GRAY);
        
        panelEstado.add(lblEstado);
        panelEstado.add(comboEstadoMatricula);
        panelEstado.add(btnBuscarPorEstado);
        panelEstado.add(btnCambiarEstado);
        panelEstado.add(lblInfoEstado);
        
        panelSuperior.add(panelBusqueda);
        panelSuperior.add(panelCarrera);
        panelSuperior.add(panelAgregar);
        panelSuperior.add(panelCursos);
        panelSuperior.add(panelProfesores);
        panelSuperior.add(panelEstado);
      
        // ────────────────────────────────────────────────────────────────────────
        // PANEL CENTRAL: Tabla de resultados
        // ────────────────────────────────────────────────────────────────────────

        modeloTabla = new DefaultTableModel(COLUMNAS_TABLA, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblResultados = new JTable(modeloTabla);
        tblResultados.setRowHeight(ALTO_FILA_TABLA);
        tblResultados.getTableHeader().setReorderingAllowed(false);
        tblResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tblResultados);
        scroll.setBorder(BorderFactory.createTitledBorder(TITULO_PANEL_RESULTADOS));

        // ────────────────────────────────────────────────────────────────────────
        // PANEL INFERIOR: Estado y Total de estudiantes
        // ────────────────────────────────────────────────────────────────────────

        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));

        lblEstado = new JLabel(MENSAJE_INICIAL);
        lblEstado.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        lblEstado.setForeground(COLOR_ESTADO_TEXTO);

        lblTotalEstudiantes = new JLabel();
        lblTotalEstudiantes.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        lblTotalEstudiantes.setForeground(Color.BLUE);
        actualizarTotalEstudiantes();

        panelInferior.add(lblEstado, BorderLayout.WEST);
        panelInferior.add(lblTotalEstudiantes, BorderLayout.EAST);

        // ────────────────────────────────────────────────────────────────────────
        // Agregar todo al JFrame
        // ────────────────────────────────────────────────────────────────────────

        add(panelSuperior,    BorderLayout.NORTH);
        add(scroll,           BorderLayout.CENTER);
        add(panelInferior,    BorderLayout.SOUTH);
    }

    // ── Métodos de inicialización ─────────────────────────────────────────────

    
    private void cargarProfesores() {
        if (controlador != null) {
            comboProfesores.removeAllItems();
            String[] profesores = controlador.obtenerListaProfesores();
            for (String prof : profesores) {
                comboProfesores.addItem(prof);
            }
        }
    }
    
    /**
     * Carga las carreras disponibles desde el controlador al combo de búsqueda.
     */
    private void cargarCarreras() {
        if (controlador != null) {
            String[] carreras = controlador.obtenerCarrerasUnicas();
            for (String carrera : carreras) {
                cmbCarrera.addItem(carrera);
            }
        }
    }

    /**
     * Carga las carreras disponibles desde el controlador al combo de agregar.
     */
    private void cargarCarrerasAgregar() {
        if (controlador != null) {
            String[] carreras = controlador.obtenerCarrerasUnicas();
            for (String carrera : carreras) {
                cmbAgregarCarrera.addItem(carrera);
            }
        }
    }

    // ── Eventos ───────────────────────────────────────────────────────────────

    /**
     * Método que encapsula la inicialización de eventos.
     */
    private void initEventos() {
        // Evento: buscar por nombre
        btnBuscar.addActionListener((ActionEvent e) -> {
            if (controlador != null) {
                controlador.buscarEstudiante(txtNombre.getText().trim());
            }
        });

        txtNombre.addActionListener((ActionEvent e) -> btnBuscar.doClick());

        // Evento: buscar por carrera
        btnBuscarCarrera.addActionListener((ActionEvent e) -> {
            if (controlador != null) {
                String carriSelected = (String) cmbCarrera.getSelectedItem();
                if (carriSelected != null && !carriSelected.equals(OPCION_SELECCIONAR)) {
                    controlador.buscarEstudiantePorCarrera(carriSelected);
                } else {
                    mostrarError("Seleccione una carrera válida.");
                }
            }
        });

        // Evento: limpiar búsqueda
        btnLimpiar.addActionListener((ActionEvent e) -> {
            limpiarBusqueda();
        });

        // Evento: agregar nuevo estudiante
        btnAgregar.addActionListener((ActionEvent e) -> {
            if (controlador != null) {
                String nombre = txtAgregarNombre.getText().trim();
                String apellido = txtAgregarApellido.getText().trim();
                String carrera = (String) cmbAgregarCarrera.getSelectedItem();
                double promedio = (double) spinPromedio.getValue();

                if (controlador.agregarEstudiante(nombre, apellido, carrera, promedio)) {
                    // Limpiar formulario
                    txtAgregarNombre.setText("");
                    txtAgregarApellido.setText("");
                    cmbAgregarCarrera.setSelectedIndex(0);
                    spinPromedio.setValue(3.0);
                    actualizarTotalEstudiantes();
                }
            }
        });
    }

    public void mostrarEstudiante(Object[] fila) {
        limpiarTabla();
        modeloTabla.addRow(fila);
        setEstado(MENSAJE_ENCONTRADO_UNO);
    }

    public void mostrarEstudiantes(List<Object[]> filas) {
        limpiarTabla();
        if (filas == null || filas.isEmpty()) {
            setEstado(MENSAJE_SIN_RESULTADOS);
            return;
        }
        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
        setEstado(String.format(MENSAJE_ENCONTRADOS_VARIOS, filas.size()));
    }

    /**
     * Muestra un mensaje de error en la barra de estado.
     */
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        setEstado("Error: " + mensaje);
    }

    /**
     * Muestra un mensaje de información/éxito en la barra de estado.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
        setEstado(mensaje);
    }

    /**
     * Devuelve el texto ingresado en el campo de nombre.
     */
    public String getNombreBuscado() {
        return txtNombre.getText().trim();
    }

   
    public void setControlador(EstudianteController controlador) {
        this.controlador = controlador;
        cargarCarreras();
        cargarCarrerasAgregar();
        actualizarTotalEstudiantes();
        cargarProfesores();
    }


    private void actualizarTotalEstudiantes() {
        int total = (controlador != null) ? controlador.obtenerTotalEstudiantes() : 0;
        lblTotalEstudiantes.setText("Total de estudiantes: " + total);
    }

    /**
     * Limpia todos los campos de búsqueda y la tabla.
     */
    private void limpiarBusqueda() {
        txtNombre.setText("");
        cmbCarrera.setSelectedIndex(0);
        limpiarTabla();
        setEstado(MENSAJE_INICIAL);
    }

    /**
     * Limpia todas las filas de la tabla.
     */
    private void limpiarTabla() {
        modeloTabla.setRowCount(0);
    }

    /**
     * Actualiza el texto del label de estado.
     */
    private void setEstado(String texto) {
        lblEstado.setText(texto);
    }
}