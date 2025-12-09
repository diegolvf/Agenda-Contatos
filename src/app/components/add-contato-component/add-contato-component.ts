import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContatoService } from '../../services/contato-service';
import { GrupoService } from '../../services/grupo-service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-contato-component',
  standalone: false,
  templateUrl: './add-contato-component.html',
  styleUrl: './add-contato-component.css',
})
export class AddContatoComponent implements OnInit {

  formGroupContato:FormGroup;
  contato: any = {
    name: '',
    nickname: '',
    email: '',
    phonenumber: '',
    address:'',
    occupation: [''],
    birthday: null,
    grupoName: '',
    grupoId: 0
  };
  
  grupos: any[] = [];

  constructor(
    private contatoService: ContatoService,
    private formBuilder: FormBuilder,
    private grupoService: GrupoService,
    private router: Router
  ) { 
    this.formGroupContato = formBuilder.group({
      name: [''],
      nickname: [''],
      email: [''],
      phonenumber: [''], 
      address: [''],
      occupation: [''],
      birthday: [''],
      grupoName:'',
      grupoId: ['']
  
    })
  }

  ngOnInit(): void {
    this.grupoService.findAll().subscribe(g => this.grupos = g);
  }

  salvar() {
    console.log(this.formGroupContato.value)
    this.contatoService.create(this.formGroupContato.value).subscribe(() => {
      this.router.navigate(['/contatos']);
    });
  }

  getGrupo( name: string, id: any){
    this.formGroupContato.patchValue({
        grupoName: name, 
        grupoId: id 
    });
  }

  onGrupoChange(selectedId: string): void {
  // Converte o valor para number (o ID vem como string do HTML)
  const id = +selectedId; 
  
  // Encontra o objeto completo do grupo na lista 'grupos'
  const grupoSelecionado = this.grupos.find(g => g.id === id);

  if (grupoSelecionado) {
    // Chama o seu método original para aplicar o nome e o id no formulário.
    this.getGrupo(grupoSelecionado.name, grupoSelecionado.id);
  } else {
    // Caso o usuário selecione uma opção inválida ou placeholder
    this.getGrupo('', null); 
  }
}

}
