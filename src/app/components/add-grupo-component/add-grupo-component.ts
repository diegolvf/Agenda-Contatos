import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { GrupoService } from '../../services/grupo-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-grupo-component',
  standalone: false,
  templateUrl: './add-grupo-component.html',
  styleUrl: './add-grupo-component.css',
})
export class AddGrupoComponent {
  formGroupContato: FormGroup;
  contato: any = {
    name: '',
    nickname: '',
    email: '',
    phonenumber: '',
    address: '',
    occupation: [''],
    birthday: null,
    grupoName: '',
    grupoId: 0
  };

  grupos: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private grupoService: GrupoService,
    private router: Router
  ) {
    this.formGroupContato = formBuilder.group({
      name: ['']
    })
  }

  ngOnInit(): void {
    this.grupoService.findAll().subscribe(g => this.grupos = g);
  }

  salvar() {
    console.log(this.formGroupContato.value)
    this.grupoService.create(this.formGroupContato.value).subscribe(() => {
      this.router.navigate(['/grupos']);
    });
  }

}
