
package com.portfoliobrenda.blt.Security.Repository;

import com.portfoliobrenda.blt.Security.Entity.Rol;
import com.portfoliobrenda.blt.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional <Rol> findByRolNombre(RolNombre rolNombre);
}
