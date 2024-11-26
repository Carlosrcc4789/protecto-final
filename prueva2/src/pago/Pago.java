package pago;

import cliente.Cliente;

public class Pago {
    private int id;
    private Cliente cliente;
    private double monto;
    private String fechaPago;
    private String metodoPago;

    // Constructor, getters y setters
    public Pago(int id, Cliente cliente, double monto, String fechaPago, String metodoPago) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
// Getters y setters aquí
}
