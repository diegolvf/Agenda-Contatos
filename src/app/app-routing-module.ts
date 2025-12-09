import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { ContatoDetalheComponent } from './components/contato-info-component/info-contato-component';
import { GrupoComponent } from './components/grupo-component/grupo-component';
import { ContatoComponent } from './components/contato-component/contato-component';
import { AddContatoComponent } from './components/add-contato-component/add-contato-component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  {path: 'contatos', component: ContatoComponent},
  { path: 'contatos/:id', component: ContatoDetalheComponent },
  { path: 'contatos/info/:id', component: ContatoDetalheComponent },
  { path: 'grupos', component: GrupoComponent },
  { path: 'adicionar', component: AddContatoComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
