package R2_2_JDBC_MySQL;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private int idCasa;
    private int anioCurso;
    private String fechaNacimiento;

    public Estudiante(int id, String nombre, String apellido, int idCasa, int anioCurso, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idCasa = idCasa;
        this.anioCurso = anioCurso;
        this.fechaNacimiento = fechaNacimiento;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public int getAnioCurso() {
        return anioCurso;
    }

    public void setAnioCurso(int anioCurso) {
        this.anioCurso = anioCurso;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", idCasa=" + idCasa +
                ", anioCurso=" + anioCurso +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
