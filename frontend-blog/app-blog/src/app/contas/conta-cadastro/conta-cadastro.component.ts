import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Conta } from '../../blog-model/blog-enum/tipoConta';
import { DateAdapter, NativeDateAdapter } from '@angular/material';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-conta-cadastro',
  templateUrl: './conta-cadastro.component.html',
  styleUrls: ['./conta-cadastro.component.scss']
})
export class ContaCadastroComponent implements OnInit {

  assetContaForm: FormGroup;
  tipoConta = [
    { id: '1', name: Conta.ALUGUEL },
    { id: '2', name: Conta.AGUA },
    { id: '3', name: Conta.LUZ },
    { id: '4', name: Conta.GAS },
    { id: '5', name: Conta.COMBO },
    { id: '6', name: Conta.CC },
    { id: '7', name: Conta.CELULAR },
    { id: '8', name: 'OUTRO' }
  ];

  constructor(private formBuilder: FormBuilder, dateAdapter: DateAdapter<NativeDateAdapter>) {
    dateAdapter.setLocale('pt-BR');
  }

  ngOnInit() {
    this.assetContaForm = this.formBuilder.group({
      conta: [''],
      assunto: [''],
      dataVencimento: ['']
      /* pessoaNome: ['', Validators.compose([Validators.required])],
      emailCliente: ['', Validators.email],
      registroAtivo: ['', Validators.compose([Validators.required])], */
    });
  }

  verifyAddClient(event: KeyboardEvent) {
    const formValues = this.assetContaForm.value;
    const dataVencimento = formValues.dataVencimento;
    console.log(dataVencimento);
  }

  getFormatDate(): any {
    const formValues = this.assetContaForm.value;
    const dataVencimento = formValues.dataVencimento;
    if (dataVencimento != null) {
      return dataVencimento
    } else {
      return null;
    }
  }
}