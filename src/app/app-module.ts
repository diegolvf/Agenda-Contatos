import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './components/navbar-component/navbar-component';
import { HomeComponent } from './components/home-component/home-component';
import { ContatoComponent } from './components/contato-component/contato-component';
import { ContatoDetalheComponent } from './components/contato-info-component/info-contato-component';
import { GrupoComponent } from './components/grupo-component/grupo-component';
import { FooterComponent } from './components/footer-component/footer-component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientJsonpModule, provideHttpClient } from '@angular/common/http';
import { AddContatoComponent } from './components/add-contato-component/add-contato-component';




@NgModule({
  declarations: [
    App,
    NavbarComponent,
    HomeComponent,
    ContatoComponent,
    ContatoDetalheComponent,
    GrupoComponent,
    FooterComponent,
    AddContatoComponent


  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientJsonpModule,
    NgbModule
  ],
  providers: [
    provideHttpClient(),
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
