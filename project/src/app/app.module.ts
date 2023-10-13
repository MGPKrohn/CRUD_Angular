import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { IndexComponent } from './layout/index/index.component';
import { PessoaslistComponent } from './pessoas/pessoaslist/pessoaslist.component';
import { PessoasdetailsComponent } from './pessoas/pessoasdetails/pessoasdetails.component';
import { CarroComponent } from './carros/carro/carro.component';
import { CarrosdetailsComponent } from './carros/carrosdetails/carrosdetails.component';
import { CarroslistComponent } from './carros/carroslist/carroslist.component';
import { LivroComponent } from './livros/livro/livro.component';
import { LivrosdetailsComponent } from './livros/livrosdetails/livrosdetails.component';
import { LivroslistComponent } from './livros/livroslist/livroslist.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent,
    PessoaslistComponent,
    IndexComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    PessoasdetailsComponent,
    CarroComponent,
    CarrosdetailsComponent,
    CarroslistComponent,
    LivroComponent,
    LivrosdetailsComponent,
    LivroslistComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
