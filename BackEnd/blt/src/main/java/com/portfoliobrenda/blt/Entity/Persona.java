
package com.portfoliobrenda.blt.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity

public class Persona {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
   @NotNull
   @Size(min = 1, max = 50, message = "no cumple con los requerimientos")
    private String nombre;
   
   @NotNull
   @Size(min = 1, max = 50, message = "no cumple con los requerimientos")
    private String apellido;
   
   @Size(min = 1, max = 50, message = "no cumple con los requerimientos")
    private String img;

    public void setNombre(String nuevoNombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setApellido(String nuevoApellido) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setImg(String nuevoImg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   
}
