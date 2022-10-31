import { Component, OnInit } from '@angular/core';
import { Estudios } from 'src/app/model/estudios';
import { EstudiosService } from 'src/app/service/estudios.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-estudios',
  templateUrl: './estudios.component.html',
  styleUrls: ['./estudios.component.css']
})
export class EstudiosComponent implements OnInit {
  estudios: Estudios[] = [];

  constructor(private estudiosS: EstudiosService, private tokenService: TokenService) { }

  isLogged = false;

  ngOnInit(): void {
    this.cargarEstudios();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  cargarEstudios(): void {
    this.estudiosS.lista().subscribe(
      data =>{
        this.estudios = data;
      }
    )
  }

  delete(id?: number) {
    if (id != undefined) {
      this.estudiosS.delete(id).subscribe(
        data => {
          this.cargarEstudios();
        }, err => {
          alert("No es posible eliminar");
        }
      )
    }
  }
}
