package servicotecnicoapp;

import conexionBD.ConexionBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class ServicioTecnicoApp extends JFrame {

    private Connection conn;
    private JTextField nombreClienteField, direccionField, telefonoField, emailField;
    private JTextField modeloCelularField, marcaCelularField, colorCelularField, fechaIngresoField;
    private JTextField descripcionServicioField, costoServicioField;
    private JTextField montoPagoField, fechaPagoField, metodoPagoField;
    private JTextField nombreEmpleadoField, telefonoEmpleadoField, emailEmpleadoField, fechaIngresoEmpleadoField;

    private JComboBox<Integer> celularComboBox;
    private JComboBox<Servicio> servicioComboBox;
    private JTextField fechaServicioField;

    private JTextArea registrosArea;

    public ServicioTecnicoApp() {
        initComponents();
    }

    private void initComponents() {
        inicializarConexiones();
        configurarVentana();
    }

    private void inicializarConexiones() {
        try {
            conn = ConexionBD.getConnection();
        } catch (SQLException e) {
            mostrarError("Error al conectar a la base de datos.");
            System.exit(1); // Salir si no se puede conectar
        }
    }

    private void configurarVentana() {
        setTitle("Servicio Técnico - Registro");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        agregarPanelCliente(mainPanel);
        agregarPanelCelular(mainPanel);
        agregarPanelServicio(mainPanel);
        agregarPanelPago(mainPanel);
        agregarPanelEmpleado(mainPanel);
        agregarPanelRegistroServicio(mainPanel);


        agregarPanelRegistros(mainPanel);


        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
    }

    private void agregarPanelCliente(JPanel mainPanel) {
        JPanel clientePanel = crearPanel("Registrar Cliente");
        nombreClienteField = crearCampoTexto(clientePanel, "Nombre Cliente:");
        direccionField = crearCampoTexto(clientePanel, "Dirección:");
        telefonoField = crearCampoTexto(clientePanel, "Teléfono:");
        emailField = crearCampoTexto(clientePanel, "Email:");

        JButton btnAgregarCliente = crearBoton("Agregar Cliente", e -> agregarCliente());
        clientePanel.add(btnAgregarCliente);

        mainPanel.add(clientePanel);
    }

    private void agregarPanelCelular(JPanel mainPanel) {
        JPanel celularPanel = crearPanel("Registrar Celular");
        modeloCelularField = crearCampoTexto(celularPanel, "Modelo Celular:");
        marcaCelularField = crearCampoTexto(celularPanel, "Marca Celular:");
        colorCelularField = crearCampoTexto(celularPanel, "Color Celular:");
        fechaIngresoField = crearCampoTexto(celularPanel, "Fecha Ingreso:");

        JButton btnAgregarCelular = crearBoton("Agregar Celular", e -> agregarCelular());
        celularPanel.add(btnAgregarCelular);

        mainPanel.add(celularPanel);
    }

    private void agregarPanelServicio(JPanel mainPanel) {
        JPanel servicioPanel = crearPanel("Registrar Servicio");
        descripcionServicioField = crearCampoTexto(servicioPanel, "Descripción Servicio:");
        costoServicioField = crearCampoTexto(servicioPanel, "Costo Servicio:");

        JButton btnAgregarServicio = crearBoton("Agregar Servicio", e -> agregarServicio());
        servicioPanel.add(btnAgregarServicio);

        mainPanel.add(servicioPanel);
    }

    private void agregarPanelPago(JPanel mainPanel) {
        JPanel pagoPanel = crearPanel("Registrar Pago");
        montoPagoField = crearCampoTexto(pagoPanel, "Monto Pago:");
        fechaPagoField = crearCampoTexto(pagoPanel, "Fecha Pago (YYYY-MM-DD):");
        metodoPagoField = crearCampoTexto(pagoPanel, "Método Pago:");

        JButton btnAgregarPago = crearBoton("Agregar Pago", e -> agregarPago());
        pagoPanel.add(btnAgregarPago);

        mainPanel.add(pagoPanel);
    }

    private void agregarPanelEmpleado(JPanel mainPanel) {
        JPanel empleadoPanel = crearPanel("Registrar Empleado");
        nombreEmpleadoField = crearCampoTexto(empleadoPanel, "Nombre Empleado:");
        telefonoEmpleadoField = crearCampoTexto(empleadoPanel, "Teléfono Empleado:");
        emailEmpleadoField = crearCampoTexto(empleadoPanel, "Email Empleado:");
        fechaIngresoEmpleadoField = crearCampoTexto(empleadoPanel, "Fecha Ingreso (YYYY-MM-DD):");

        JButton btnAgregarEmpleado = crearBoton("Agregar Empleado", e -> agregarEmpleado());
        empleadoPanel.add(btnAgregarEmpleado);

        mainPanel.add(empleadoPanel);
    }

    private void agregarPanelRegistroServicio(JPanel mainPanel) {
        JPanel registroPanel = crearPanel("Registrar Servicio al Celular");

        celularComboBox = new JComboBox<>();
        cargarCelulares();
        registroPanel.add(new JLabel("Seleccionar Celular:"));
        registroPanel.add(celularComboBox);

        servicioComboBox = new JComboBox<>();
        cargarServicios(); // Cargamos los servicios
        registroPanel.add(new JLabel("Seleccionar Servicio:"));
        registroPanel.add(servicioComboBox);

        fechaServicioField = crearCampoTexto(registroPanel, "Fecha de Servicio (YYYY-MM-DD):");

        JButton btnAgregarRegistroServicio = crearBoton("Agregar Registro de Servicio", e -> agregarRegistroServicio());
        registroPanel.add(btnAgregarRegistroServicio);

        mainPanel.add(registroPanel);
    }

    private void agregarPanelRegistros(JPanel mainPanel) {
        JPanel registrosPanel = crearPanel("Registros");
        registrosArea = new JTextArea(10, 50);
        registrosArea.setEditable(false);
        JScrollPane registrosScrollPane = new JScrollPane(registrosArea);
        registrosPanel.add(registrosScrollPane);

        mainPanel.add(registrosPanel);
    }

    private JPanel crearPanel(String titulo) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    private JTextField crearCampoTexto(JPanel panel, String etiqueta) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = panel.getComponentCount() / 2;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        JTextField campoTexto = new JTextField(20);
        panel.add(campoTexto, gbc);

        return campoTexto;
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.addActionListener(actionListener);
        return boton;
    }

    private void agregarCliente() {
        String nombre = nombreClienteField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();

        if (validarCampos(nombre, direccion, telefono, email)) {
            String query = "INSERT INTO clientes (nombre, direccion, telefono, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, nombre);
                pst.setString(2, direccion);
                pst.setString(3, telefono);
                pst.setString(4, email);
                pst.executeUpdate();
                mostrarMensaje("Cliente agregado con éxito.");
                mostrarRegistro("Cliente agregado: " + nombre);
            } catch (SQLException e) {
                mostrarError("Error al agregar cliente.");
            }
        }
    }

    private void agregarCelular() {
        String modelo = modeloCelularField.getText();
        String marca = marcaCelularField.getText();
        String color = colorCelularField.getText();
        String fechaIngreso = fechaIngresoField.getText();

        if (validarCampos(modelo, marca, color, fechaIngreso)) {
            String query = "INSERT INTO celulares (modelo, marca, color, fecha_ingreso) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, modelo);
                pst.setString(2, marca);
                pst.setString(3, color);
                pst.setString(4, fechaIngreso);
                pst.executeUpdate();
                mostrarMensaje("Celular agregado con éxito.");
                mostrarRegistro("Celular agregado: " + modelo);
            } catch (SQLException e) {
                mostrarError("Error al agregar celular.");
            }
        }
    }

    private void agregarServicio() {
        String descripcion = descripcionServicioField.getText();
        String costoStr = costoServicioField.getText();

        if (validarCampos(descripcion, costoStr)) {
            try {
                double costo = Double.parseDouble(costoStr);
                String query = "INSERT INTO servicios (descripcion, costo) VALUES (?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setString(1, descripcion);
                    pst.setDouble(2, costo);
                    pst.executeUpdate();
                    mostrarMensaje("Servicio agregado con éxito.");
                    mostrarRegistro("Servicio agregado: " + descripcion);
                }
            } catch (NumberFormatException e) {
                mostrarError("Costo debe ser un número válido.");
            } catch (SQLException e) {
                mostrarError("Error al agregar servicio.");
            }
        }
    }

    private void agregarPago() {
        String montoStr = montoPagoField.getText();
        String fecha = fechaPagoField.getText();
        String metodo = metodoPagoField.getText();

        if (validarCampos(montoStr, fecha, metodo)) {
            try {
                double monto = Double.parseDouble(montoStr);
                String query = "INSERT INTO pagos (monto, fecha_pago, metodo_pago) VALUES (?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(query)) {
                    pst.setDouble(1, monto);
                    pst.setString(2, fecha);
                    pst.setString(3, metodo);
                    pst.executeUpdate();
                    mostrarMensaje("Pago agregado con éxito.");
                    mostrarRegistro("Pago agregado: " + monto + " con método " + metodo);
                }
            } catch (NumberFormatException e) {
                mostrarError("Monto debe ser un número válido.");
            } catch (SQLException e) {
                mostrarError("Error al agregar pago.");
            }
        }
    }

    private void agregarEmpleado() {
        String nombre = nombreEmpleadoField.getText();
        String telefono = telefonoEmpleadoField.getText();
        String email = emailEmpleadoField.getText();
        String fechaIngreso = fechaIngresoEmpleadoField.getText();

        if (validarCampos(nombre, telefono, email, fechaIngreso)) {
            String query = "INSERT INTO empleados (nombre, telefono, email, fecha_ingreso) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, nombre);
                pst.setString(2, telefono);
                pst.setString(3, email);
                pst.setString(4, fechaIngreso);
                pst.executeUpdate();
                mostrarMensaje("Empleado agregado con éxito.");
                mostrarRegistro("Empleado agregado: " + nombre);
            } catch (SQLException e) {
                mostrarError("Error al agregar empleado.");
            }
        }
    }

    private void agregarRegistroServicio() {
        Integer celularId = (Integer) celularComboBox.getSelectedItem();
        Servicio servicioSeleccionado = (Servicio) servicioComboBox.getSelectedItem();
        String fechaServicio = fechaServicioField.getText();

        if (validarCampos(fechaServicio)) {
            String query = "INSERT INTO registros_servicio (celular_id, servicio_id, fecha_servicio) VALUES (?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, celularId);
                pst.setInt(2, servicioSeleccionado.getId());
                pst.setString(3, fechaServicio);
                pst.executeUpdate();
                mostrarMensaje("Registro de servicio agregado con éxito.");
                mostrarRegistro("Registro de servicio agregado: Celular ID " + celularId + ", Servicio: " + servicioSeleccionado.getDescripcion());
            } catch (SQLException e) {
                mostrarError("Error al agregar registro de servicio.");
            }
        }
    }

    private void cargarCelulares() {
        cargarComboBox("SELECT id FROM celulares", celularComboBox);
    }

    private void cargarServicios() {
        String query = "SELECT id, descripcion FROM servicios";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                servicioComboBox.addItem(new Servicio(id, descripcion));
            }
        } catch (SQLException e) {
            mostrarError("Error al cargar servicios.");
        }
    }

    private void cargarComboBox(String query, JComboBox<Integer> comboBox) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                comboBox.addItem(rs.getInt("id"));
            }
        } catch (SQLException e) {
            mostrarError("Error al cargar datos.");
        }
    }

    private boolean validarCampos(String... campos) {
        for (String campo : campos) {
            if (campo.isEmpty()) {
                mostrarError("Por favor, complete todos los campos.");
                return false;
            }
        }
        return true;
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarRegistro(String registro) {
        registrosArea.append(registro + "\n");
    }

    private class Servicio {
        private int id;
        private String descripcion;

        public Servicio(int id, String descripcion) {
            this.id = id;
            this.descripcion = descripcion;
        }

        public int getId() {
            return id;
        }

        public String getDescripcion() {
            return descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ServicioTecnicoApp().setVisible(true);
        });
    }
}
