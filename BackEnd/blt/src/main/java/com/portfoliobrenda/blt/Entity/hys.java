package com.portfoliobrenda.blt.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class hys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int porsentaje;

    public hys() {
    }

    public hys(String nombre, int porsentaje) {
        this.nombre = nombre;
        this.porsentaje = porsentaje;
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

    public int getPorsentaje() {
        return porsentaje;
    }

    public void setPorsentaje(int porsentaje) {
        this.porsentaje = porsentaje;
    }
    
    
}
