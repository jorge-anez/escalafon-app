/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uto.dea.jsf.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author VIIII1
 */
public class TablaEscalafon implements Serializable {
    private UniversidadBean ub;
    private FacultadBean fb;
    private CarreraBean cb;
    private long gestion;
    
    private List<EscalafonBean> list;
    
    private Map<Long, Long> listaGestion;

    public TablaEscalafon() {
        Calendar cal= Calendar.getInstance();
        this.gestion= cal.get(Calendar.YEAR) ;
    }
    
    public void crearMapaListaGestion() {
        listaGestion=new LinkedHashMap<>();
        for(long i = gestion-20;i<=(gestion+20);i++ ){
            listaGestion.put(i, i);
        }
    }
    
}
