package dea.modelo;
// Generated 16-may-2015 14:51:40 by Hibernate Tools 4.3.1



/**
 * RequiereMateriaId generated by hbm2java
 */
public class RequiereMateriaId  implements java.io.Serializable {


     private long idMateria;
     private long idMateriaRequisito;

    public RequiereMateriaId() {
    }

    public RequiereMateriaId(long idMateria, long idMateriaRequisito) {
       this.idMateria = idMateria;
       this.idMateriaRequisito = idMateriaRequisito;
    }
   
    public long getIdMateria() {
        return this.idMateria;
    }
    
    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }
    public long getIdMateriaRequisito() {
        return this.idMateriaRequisito;
    }
    
    public void setIdMateriaRequisito(long idMateriaRequisito) {
        this.idMateriaRequisito = idMateriaRequisito;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RequiereMateriaId) ) return false;
		 RequiereMateriaId castOther = ( RequiereMateriaId ) other; 
         
		 return (this.getIdMateria()==castOther.getIdMateria())
 && (this.getIdMateriaRequisito()==castOther.getIdMateriaRequisito());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIdMateria();
         result = 37 * result + (int) this.getIdMateriaRequisito();
         return result;
   }   


}


