import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { ContatoDetalheComponent } from './components/contato-info-component/info-contato-component';
import { GrupoComponent } from './components/grupo-component/grupo-component';
import { ContatoComponent } from './components/contato-component/contato-component';
import { AddContatoComponent } from './components/add-contato-component/add-contato-component';
import { GrupoInfo } from './components/grupo-info/grupo-info';
import { AddGrupoComponent } from './components/add-grupo-component/add-grupo-component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'contatos', component: ContatoComponent },
  { path: 'contatos/:id', component: ContatoDetalheComponent },
  { path: 'contatos/info/:id', component: ContatoDetalheComponent },
  { path: 'grupos', component: GrupoComponent },
  { path: 'grupos/info/:id', component: GrupoInfo },
  { path: 'adicionar', component: AddContatoComponent },
  { path: 'add', component: AddGrupoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
