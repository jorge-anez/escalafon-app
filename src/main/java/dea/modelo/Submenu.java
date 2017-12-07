package dea.modelo;
// Generated 30-sep-2015 12:35:24 by Hibernate Tools 4.3.1



/**
 * Submenu generated by hbm2java
 */
public class Submenu  implements java.io.Serializable {


     private int idSubmenu;
     private Menu menu;
     private String submenu;
     private String link;
     private String icon;

    public Submenu() {
    }

	
    public Submenu(int idSubmenu) {
        this.idSubmenu = idSubmenu;
    }
    public Submenu(int idSubmenu, Menu menu, String submenu, String link, String icon) {
       this.idSubmenu = idSubmenu;
       this.menu = menu;
       this.submenu = submenu;
       this.link = link;
       this.icon = icon;
    }
   
    public int getIdSubmenu() {
        return this.idSubmenu;
    }
    
    public void setIdSubmenu(int idSubmenu) {
        this.idSubmenu = idSubmenu;
    }
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public String getSubmenu() {
        return this.submenu;
    }
    
    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }




}


