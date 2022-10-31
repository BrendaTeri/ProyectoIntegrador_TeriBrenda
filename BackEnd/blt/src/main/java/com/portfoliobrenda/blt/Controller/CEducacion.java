package com.portfoliobrenda.blt.Controller;

import com.portfoliobrenda.blt.Dto.dtoEducacion;
import com.portfoliobrenda.blt.Entity.Educacion;
import com.portfoliobrenda.blt.Security.Contoller.Mensaje;
import com.portfoliobrenda.blt.Service.SEducacion;
import java.util.List;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = " https://backendblt.herokuapp.com/")
public class CEducacion {

    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoEd) {
        if (StringUtils.isBlank(dtoEd.getNombreE())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombreE(dtoEd.getNombreE())) {
            return new ResponseEntity(new Mensaje("Ya existe"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoEd.getNombreE(), dtoEd.getDescripcionE());
        sEducacion.save(educacion);

        return new ResponseEntity(new Mensaje("Edcucacion creada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoEd) {
        //Ya exise?
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara experiencias
        if (sEducacion.existsByNombreE(dtoEd.getNombreE()) && sEducacion.getByNombreE(dtoEd.getNombreE()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No debe estar vacio
        if (StringUtils.isBlank(dtoEd.getNombreE())) {
            return new ResponseEntity(new Mensaje("Campo obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreE(dtoEd.getNombreE());
        educacion.setDescripcionE(dtoEd.getDescripcionE());

        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //Ya exise?
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        sEducacion.delete(id);

        return new ResponseEntity(new Mensaje("Educacion Eliminda"), HttpStatus.OK);
    }

}
