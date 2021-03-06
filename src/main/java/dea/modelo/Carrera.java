package dea.modelo;
// Generated 16-may-2015 14:51:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Carrera generated by hbm2java
 */
public class Carrera  implements java.io.Serializable {


     private long idCarrera;
     private Facultad facultad;
     private String siglaCarrera;
     private String nombre;
     private Set materias = new HashSet(0);
     private Set docenteEscalafons = new HashSet(0);

    public Carrera() {
    }

	
    public Carrera(long idCarrera, Facultad facultad, String siglaCarrera) {
        this.idCarrera = idCarrera;
        this.facultad = facultad;
        this.siglaCarrera = siglaCarrera;
    }
    public Carrera(long idCarrera, Facultad facultad, String siglaCarrera, String nombre, Set materias, Set docenteEscalafons) {
       this.idCarrera = idCarrera;
       this.facultad = facultad;
       this.siglaCarrera = siglaCarrera;
       this.nombre = nombre;
       this.materias = materias;
       this.docenteEscalafons = docenteEscalafons;
    }
   
    public long getIdCarrera() {
        return this.idCarrera;
    }
    
    public void setIdCarrera(long idCarrera) {
        this.idCarrera = idCarrera;
    }
    public Facultad getFacultad() {
        return this.facultad;
    }
    
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    public String getSiglaCarrera() {
        return this.siglaCarrera;
    }
    
    public void setSiglaCarrera(String siglaCarrera) {
        this.siglaCarrera = siglaCarrera;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getMaterias() {
        return this.materias;
    }
    
    public void setMaterias(Set materias) {
        this.materias = materias;
    }
    public Set getDocenteEscalafons() {
        return this.docenteEscalafons;
    }
    
    public void setDocenteEscalafons(Set docenteEscalafons) {
        this.docenteEscalafons = docenteEscalafons;
    }




}


