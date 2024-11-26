package celular;

import cliente.Cliente;

public class Celular {
    private int id;
    private String modelo;
    private String marca;
    private String color;
    private Cliente cliente;
    private String fechaIngreso;

    public Celular(int id, String modelo, String marca, String color, Cliente cliente, String fechaIngreso) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
        this.cliente = cliente;
        this.fechaIngreso = fechaIngreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}
