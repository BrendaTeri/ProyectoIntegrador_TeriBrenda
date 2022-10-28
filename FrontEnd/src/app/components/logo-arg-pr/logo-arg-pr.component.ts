import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logo-arg-pr',
  templateUrl: './logo-arg-pr.component.html',
  styleUrls: ['./logo-arg-pr.component.css']
})
export class LogoArgPrComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.router.navigate(['/login'])
  }
}
