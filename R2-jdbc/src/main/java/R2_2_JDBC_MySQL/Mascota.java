package R2_2_JDBC_MySQL;

public class Mascota {
    private int id;
    private String nombre;
    private String tipo;
    private int idEstudiante;


    public Mascota(int id, String nombre, String tipo, int idEstudiante) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idEstudiante = idEstudiante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idEstudiante=" + idEstudiante +
                '}';
    }
}
