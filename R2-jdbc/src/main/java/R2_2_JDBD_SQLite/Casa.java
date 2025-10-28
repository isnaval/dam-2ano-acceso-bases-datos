package R2_2_JDBD_SQLite;

public class Casa {
    private int id;
    private String nombre;

    public Casa(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Casa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
