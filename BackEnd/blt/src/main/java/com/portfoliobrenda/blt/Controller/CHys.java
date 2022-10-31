package com.portfoliobrenda.blt.Controller;

import com.portfoliobrenda.blt.Dto.dtoHys;
import com.portfoliobrenda.blt.Entity.hys;
import com.portfoliobrenda.blt.Security.Contoller.Mensaje;
import com.portfoliobrenda.blt.Service.Shys;
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
@CrossOrigin(origins = " https://backendblt.herokuapp.com/")
@RequestMapping("/skill")
public class CHys {
    @Autowired
    Shys shys;

    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list() {
        List<hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (shys.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Ya existe"), HttpStatus.BAD_REQUEST);
        }

        hys hYs = new hys(dtohys.getNombre(), dtohys.getPorsentaje());
        shys.save(hYs);

        return new ResponseEntity(new Mensaje("Skill creada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys) {
        //Ya exise?
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        //Compara experiencias
        if (shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe"), HttpStatus.BAD_REQUEST);
        }

        //No debe estar vacio
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Cmpo obligatorio"), HttpStatus.BAD_REQUEST);
        }

        hys hYs = shys.getOne(id).get();
        hYs.setNombre(dtohys.getNombre());
        hYs.setPorsentaje(dtohys.getPorsentaje());

        shys.save(hYs);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //Ya exise?
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        shys.delete(id);
        return new ResponseEntity(new Mensaje("Skill Eliminda"), HttpStatus.OK);
    }

}
