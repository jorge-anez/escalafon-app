/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import bo.edu.uto.dea.jhs.bo.IUniversidadBO;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VIIII1
 */
public class UniversidadBean implements Serializable {

    /**
     * @return the estados
     */
    public String[] getEstados() {
        return estados;
    }
    private String siglaUniversidad;
    private String nombre;
    private String estado;
    private List<UniversidadBean> lista;
    private Map<String,String> listaMap;
    private IUniversidadBO ImplementUniversidadBO;
    
    private final static String[] estados;
    
    static {
        estados=new String[3];
        estados[0]="ACTIVO";
        estados[1]="SUSPENDIDO";
        estados[2]="ELIMINADO";
    }

    public UniversidadBean() {
        super();
        this.siglaUniversidad = "sigla";
        this.nombre = "nombre";
        this.estado = "estado";
    }

    /**
     * @return the siglaUniversidad
     */
    public String getSiglaUniversidad() {
        return siglaUniversidad;
    }

    /**
     * @param siglaUniversidad the siglaUniversidad to set
     */
    public void setSiglaUniversidad(String siglaUniversidad) {
        this.siglaUniversidad = siglaUniversidad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the lista
     */
    public List<UniversidadBean> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<UniversidadBean> lista) {
        this.lista = lista;
    }

    /**
     * @return the ImplementUniversidadBO
     */
    public IUniversidadBO getImplementUniversidadBO() {
        return ImplementUniversidadBO;
    }

    /**
     * @param ImplementUniversidadBO the ImplementUniversidadBO to set
     */
    public void setImplementUniversidadBO(IUniversidadBO ImplementUniversidadBO) {
        this.ImplementUniversidadBO = ImplementUniversidadBO;
    }

    @PostConstruct
    public void obtenerUniversidads() {
        setLista(getImplementUniversidadBO().obtenerUniversidads());
        mapearLista();
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String crearUniversidad() {
        Long respuesta = new Long(0);
        FacesMessage msg=null;
        respuesta = getImplementUniversidadBO().crearUniversidad(this);
        obtenerUniversidads();
        if( respuesta.longValue()==0 ){
            msg=new FacesMessage( FacesMessage.SEVERITY_INFO,"Alerta","La universidad con sigla "+getSiglaUniversidad()+" Ya existe");
        }
        else{
            msg=new FacesMessage( FacesMessage.SEVERITY_INFO,"Satisfactorio","La universidad fue creada");
            setSiglaUniversidad("sigla");
            setNombre("nombre");
        }        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }
    
    public void onEdit( RowEditEvent event ){
        FacesMessage msg=null;
        Long respuesta=new Long(0);
        setSiglaUniversidad( ((UniversidadBean) event.getObject() ).getSiglaUniversidad() );
        setNombre(((UniversidadBean) event.getObject() ).getNombre() );
        setEstado(((UniversidadBean) event.getObject() ).getEstado() );
        respuesta=ImplementUniversidadBO.modificarUniversidad( this );
        obtenerUniversidads();
        System.out.println("1 "+getSiglaUniversidad());
        System.out.println("2 "+getNombre());
        System.out.println("3 "+getEstado());
        System.out.println("Resultado "+respuesta);
        if( respuesta.longValue()==1 ){
            msg=new FacesMessage( "Universidad fue Modificado",((UniversidadBean) event.getObject() ).getNombre() );
        }
        else{
            msg=new FacesMessage( "No se encontro Universidad","" );
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onCancel( RowEditEvent event ){
        obtenerUniversidads();
        FacesMessage msg=new FacesMessage( "Modificacion cancelada",((UniversidadBean) event.getObject() ).getNombre() );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the listaMap
     */
    public Map<String,String> getListaMap() {
        return listaMap;
    }

    /**
     * @param listaMap the listaMap to set
     */
    public void setListaMap(Map<String,String> listaMap) {
        this.listaMap = listaMap;
    }
    
    public void mapearLista(){
        listaMap=new LinkedHashMap<>();
        for( UniversidadBean obj: getLista() ){
            listaMap.put(obj.getNombre(),obj.getSiglaUniversidad());
        }
    }
    
    public void universidadSeleccionada(String siglaUniversidad){
        System.out.println("AQUII "+siglaUniversidad);
        FacesMessage msg=null;
        if( getSiglaUniversidad().compareTo("")!=0 ){
            msg=new FacesMessage( "Cargando Facultades","" );
        }
        else{
            msg=new FacesMessage( "Seleccione una Universidad","" );
        }       
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
