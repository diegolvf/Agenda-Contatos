import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContatoService } from '../../services/contato-service';
import { GrupoService } from '../../services/grupo-service';
import { Contato } from '../../models/contato-model';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-info-contato-component',
  standalone: false,
  templateUrl: './info-contato-component.html',
  styleUrl: './info-contato-component.css',
})
export class ContatoDetalheComponent implements OnInit {

  id: number | null = null;
  grupos: any[] = [];
  form!: FormGroup;   // <-- Reactive Forms!

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private contatoService: ContatoService,
    private grupoService: GrupoService
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    // Criar o formulário vazio
    this.form = this.fb.group({
      name: [''],
      nickname: [''],
      email: [''],
      phonenumber: [''],
      address: [''],
      occupation: [''],
      birthday: [''],
      grupoId: [0]
    });

    // Carregar dados do contato para preencher o formulário
    this.contatoService.findById(this.id).subscribe(res => {
      this.form.patchValue({
        name: res.name,
        nickname: res.nickname,
        email: res.email,
        phonenumber: res.phonenumber,
        address: res.address,
        occupation: res.occupation,
        birthday: res.birthday,
        grupoId: res.grupoId
      });
    });

    // Carregar lista de grupos
    this.grupoService.findAll().subscribe(res => {
      this.grupos = res;
    });
  }

  salvar() {
    if (this.id && this.form.valid) {
      this.contatoService.update(this.id, this.form.value).subscribe(() => {
        this.router.navigate(['/contatos']);
      });
    }
  }
}