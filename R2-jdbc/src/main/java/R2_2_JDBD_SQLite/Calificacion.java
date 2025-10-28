package R2_2_JDBD_SQLite;

public class Calificacion {
    private int idEstudiante;
    private int idAsignatura;
    private int calificacion;

    public Calificacion(int idEstudiante, int idAsignatura, int calificacion) {
        this.idEstudiante = idEstudiante;
        this.idAsignatura = idAsignatura;
        this.calificacion = calificacion;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "idEstudiante=" + idEstudiante +
                ", idAsignatura=" + idAsignatura +
                ", calificacion=" + calificacion +
                '}';
    }
}
