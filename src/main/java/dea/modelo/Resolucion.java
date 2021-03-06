package dea.modelo;
// Generated 30-sep-2015 12:35:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Resolucion generated by hbm2java
 */
public class Resolucion  implements java.io.Serializable {


     private long tipoRes;
     private String resolucion;
     private Set escalafons = new HashSet(0);

    public Resolucion() {
    }

	
    public Resolucion(long tipoRes) {
        this.tipoRes = tipoRes;
    }
    public Resolucion(long tipoRes, String resolucion, Set escalafons) {
       this.tipoRes = tipoRes;
       this.resolucion = resolucion;
       this.escalafons = escalafons;
    }
   
    public long getTipoRes() {
        return this.tipoRes;
    }
    
    public void setTipoRes(long tipoRes) {
        this.tipoRes = tipoRes;
    }
    public String getResolucion() {
        return this.resolucion;
    }
    
    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    public Set getEscalafons() {
        return this.escalafons;
    }
    
    public void setEscalafons(Set escalafons) {
        this.escalafons = escalafons;
    }




}


