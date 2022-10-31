package com.portfoliobrenda.blt.Dto;

import javax.validation.constraints.NotBlank;


public class dtoHys {
    @NotBlank
    private String nombre;
    @NotBlank
    private int porsentaje;

    public dtoHys() {
    }

    public dtoHys(String nombre, int porsentaje) {
        this.nombre = nombre;
        this.porsentaje = porsentaje;
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
