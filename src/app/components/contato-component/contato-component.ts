import { Component, OnInit } from '@angular/core';
import { ContatoService } from '../../services/contato-service';
import { Contato } from '../../models/contato-model';

@Component({
  selector: 'app-contato',
  standalone: false,
  templateUrl: './contato-component.html',
  styleUrl: './contato-component.css',
})
export class ContatoComponent implements OnInit {

  contatos: Contato[] = [];
  termoPesquisa: string ='';

  constructor(private service: ContatoService) {}

  ngOnInit(): void {
    this.loadContatos();
  }

  loadContatos() {
    this.service.findAll().subscribe(res => {
      this.contatos = res;
    });
  }

  pesquisar() {
    if (this.termoPesquisa.trim() === '') {
      this.loadContatos();
      return;
    }

    this.service.search(this.termoPesquisa).subscribe(res => {
      this.contatos = res;
    });
  }

  deletar(id: number) {
    this.service.delete(id).subscribe(() => {
      this.contatos = this.contatos.filter(c => c.id !== id);
    });
  }
}
