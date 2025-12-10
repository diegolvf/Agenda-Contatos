import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Grupo } from '../../models/grupo-model';
import { GrupoService } from '../../services/grupo-service';

@Component({
  selector: 'app-grupo-component',
  standalone: false,
  templateUrl: './grupo-component.html',
  styleUrl: './grupo-component.css',
})
export class GrupoComponent implements OnInit {

  grupos: Grupo[] = [];
  termoPesquisa: string = '';

  constructor(
    private grupoService: GrupoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadGrupos();
  }

  loadGrupos() {
    this.grupoService.findAll().subscribe(res => {
      this.grupos = res;
    });
  }

  novo() {
    this.router.navigate(['add']);
  }

  editar(id: number) {
    this.router.navigate(['/grupos/editar', id]);
  }

  excluir(id: number) {
    if (confirm("Tem certeza que deseja excluir este grupo?")) {

      this.grupoService.delete(id).subscribe(() => {
        this.loadGrupos();
      });
    }
  }
}