import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GrupoService } from '../../services/grupo-service';

@Component({
  selector: 'app-grupo-info',
  standalone: false,
  templateUrl: './grupo-info.html',
  styleUrl: './grupo-info.css',
})
export class GrupoInfo {
  id: number | null = null;
  form!: FormGroup;   // <-- Reactive Forms!

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private grupoService: GrupoService
  ) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.form = this.fb.group({
      name: ['']
    });

    // Carregar dados do contato para preencher o formulário
    this.grupoService.findById(this.id).subscribe(res => {
      this.form.patchValue({
        name: res.name
      });
    });
  }

  salvar() {
    if (this.id && this.form.valid) {
      this.grupoService.update(this.id, this.form.value).subscribe(() => {
        this.router.navigate(['/grupos']);
      });
    }
  }
} 
