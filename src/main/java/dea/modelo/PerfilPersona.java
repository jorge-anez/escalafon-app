package dea.modelo;
// Generated 30-sep-2015 12:35:24 by Hibernate Tools 4.3.1



/**
 * PerfilPersona generated by hbm2java
 */
public class PerfilPersona  implements java.io.Serializable {


     private int idPefilPersona;
     private Perfil perfil;
     private Persona persona;

    public PerfilPersona() {
    }

	
    public PerfilPersona(int idPefilPersona) {
        this.idPefilPersona = idPefilPersona;
    }
    public PerfilPersona(int idPefilPersona, Perfil perfil, Persona persona) {
       this.idPefilPersona = idPefilPersona;
       this.perfil = perfil;
       this.persona = persona;
    }
   
    public int getIdPefilPersona() {
        return this.idPefilPersona;
    }
    
    public void setIdPefilPersona(int idPefilPersona) {
        this.idPefilPersona = idPefilPersona;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }




}

