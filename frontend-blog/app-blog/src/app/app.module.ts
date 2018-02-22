import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './pessoa/menu/menu.component';
import { HomeComponent } from './home/home.component';
import { ConsultaComponent } from './pessoa/consulta/consulta.component';
import { CadastroComponent } from './pessoa/cadastro/cadastro.component';
import { HttpModule } from '@angular/http';
import { routing } from '../app.routes/app.routs';
import { PessoaService } from './services/pessoa.service';
import { ConfigService } from './services/config.service';
import { SharedModule } from './shered/shared.module';


@NgModule({
  declarations: [
    AppComponent,
    AppComponent,
    MenuComponent,
    HomeComponent,
    ConsultaComponent,
    CadastroComponent,  
  ],
  imports: [
    BrowserModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    routing,
    SharedModule
  ],
  providers: [ConfigService, PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
