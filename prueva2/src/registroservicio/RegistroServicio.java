package registroservicio;

public class RegistroServicio {
    private int id;
    private int celularId;  // Cambio a celular_id
    private int servicioId;  // Cambio a servicio_id
    private String fechaServicio;

    public RegistroServicio(int id, int celularId, int servicioId, String fechaServicio) {
        this.id = id;
        this.celularId = celularId;
        this.servicioId = servicioId;
        this.fechaServicio = fechaServicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCelularId() {
        return celularId;
    }

    public void setCelularId(int celularId) {
        this.celularId = celularId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        this.fechaServicio = fechaServicio;
    }
}
