package R2_2_JDBC_MySQL;

public class Asignatura {
    private int id;
    private String nombre;
    private String aula;
    private boolean esObligatoria;
    private int idProfesor;

    public Asignatura(String nombre, int id, String aula, boolean esObligatoria, int idProfesor) {
                this.nombre = nombre;
        this.id = id;
        this.aula = aula;
        this.esObligatoria = esObligatoria;
        this.idProfesor = idProfesor;
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

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public boolean isEsObligatoria() {
        return esObligatoria;
    }

    public void setEsObligatoria(boolean esObligatoria) {
        this.esObligatoria = esObligatoria;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", aula='" + aula + '\'' +
                ", esObligatoria=" + esObligatoria +
                ", idProfesor=" + idProfesor +
                '}';
    }
}
