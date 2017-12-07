package dea.modelo;
// Generated 30-sep-2015 12:35:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Menu generated by hbm2java
 */
public class Menu  implements java.io.Serializable {


     private int idMenu;
     private Perfil perfil;
     private String menu;
     private Set submenus = new HashSet(0);

    public Menu() {
    }

	
    public Menu(int idMenu) {
        this.idMenu = idMenu;
    }
    public Menu(int idMenu, Perfil perfil, String menu, Set submenus) {
       this.idMenu = idMenu;
       this.perfil = perfil;
       this.menu = menu;
       this.submenus = submenus;
    }
   
    public int getIdMenu() {
        return this.idMenu;
    }
    
    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public String getMenu() {
        return this.menu;
    }
    
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public Set getSubmenus() {
        return this.submenus;
    }
    
    public void setSubmenus(Set submenus) {
        this.submenus = submenus;
    }




}


