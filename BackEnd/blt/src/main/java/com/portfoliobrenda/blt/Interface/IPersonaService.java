
package com.portfoliobrenda.blt.Interface;

import com.portfoliobrenda.blt.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    //Trae una list de personas
    public List<Persona> getPersona();
        
    //Guardar un obj tipo Persona
   public void savePersona(Persona persona);
    
    //Eliminar un obj por id
    public void deletePersona(Long id);
    
    //Buscar una Persona por id
    public Persona findPersona(Long id);
}
