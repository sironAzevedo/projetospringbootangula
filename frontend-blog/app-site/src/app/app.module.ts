import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';

import {ConfigService} from './services/config.service';
import {PessoaService} from './services/pessoa.service';
import { MenuComponent } from 'app/pessoa/menu/menu.component';
import { HomeComponent } from 'app/home/home.component';
import { ConsultaComponent } from 'app/pessoa/consulta/consulta.component';
import { CadastroComponent } from 'app/pessoa/cadastro/cadastro.component';

import {routing} from './../app.routes';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomeComponent,
    ConsultaComponent,
    CadastroComponent,    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing  
  ],
  providers: [ConfigService, PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
