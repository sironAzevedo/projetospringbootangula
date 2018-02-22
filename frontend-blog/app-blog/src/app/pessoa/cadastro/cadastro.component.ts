import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { PessoaService } from '../../services/pessoa.service';
import { Pessoa } from '../../services/pessoa';
import { Response } from '../../services/response';
import { Observable } from 'rxjs/Observable';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
    selector: 'app-cadastro-pessoa',
    templateUrl: './cadastro.component.html',
    styleUrls: ["./cadastro.component.css"]
})
export class CadastroComponent implements OnInit, OnDestroy {

    private titulo: string;
    private pessoa: Pessoa = new Pessoa();
    assetCadastroForm: FormGroup;
    destroy$ = new Subject();

    constructor(
        private formBuilder: FormBuilder,
        private pessoaService: PessoaService,
        private router: Router,
        private activatedRoute: ActivatedRoute) { }

    /*CARREGADO NA INICIALIZAÇÃO DO COMPONENTE */
    ngOnInit() {

        this.assetCadastroForm = this.formBuilder.group({
            pessoaCodigo: [''],
            pessoaNome: [''],
            emailCliente: [''],
            registroAtivo: ['']
        });


        this.activatedRoute.params.subscribe(parametro => {

            if (parametro.pessoaCodigo == undefined) {

                this.titulo = "Novo Cadastro de Pessoa";
            }
            else {

                this.titulo = "Editar Cadastro de Pessoa";
                this.pessoaService.getPessoa(Number(parametro.pessoaCodigo)).subscribe(res => this.pessoa = res);
            }

        });
    }

    /*FUNÇÃO PARA SALVAR UM NOVO REGISTRO OU ALTERAÇÃO EM UM REGISTRO EXISTENTE */
    salvar() {

        const formValue = this.assetCadastroForm.value;

        const documentInput: Pessoa = {
            codigo: formValue.pessoaCodigo,
            nome: formValue.pessoaNome,
            ativo: formValue.registroAtivo
        }

        if (formValue.pessoaCodigo == undefined) {

            this.pessoaService.addPessoa(documentInput).subscribe(response => {

                //PEGA O RESPONSE DO RETORNO DO SERVIÇO
                let res: Response = <Response>response;

                /*SE RETORNOU 1 DEVEMOS MOSTRAR A MENSAGEM DE SUCESSO
                E LIMPAR O FORMULÁRIO PARA INSERIR UM NOVO REGISTRO*/
                if (res.codigo == 1) {
                    alert(res.mensagem);
                    this.resetPessoa();
                }
                else {
                    /*
                    ESSA MENSAGEM VAI SER MOSTRADA CASO OCORRA ALGUMA EXCEPTION
                    NO SERVIDOR (CODIGO = 0)*/
                    alert(res.mensagem);
                }
            },
                (error) => {
                    /**AQUI VAMOS MOSTRAR OS ERROS NÃO TRATADOS
                      EXEMPLO: SE APLICAÇÃO NÃO CONSEGUIR FAZER UMA REQUEST NA API                        */
                    alert(error);
                });
        } else {

            /*AQUI VAMOS ATUALIZAR AS INFORMAÇÕES DE UM REGISTRO EXISTENTE */
            this.pessoaService.atualizarPessoa(documentInput).subscribe(response => {

                //PEGA O RESPONSE DO RETORNO DO SERVIÇO
                let res: Response = <Response>response;

                /*SE RETORNOU 1 DEVEMOS MOSTRAR A MENSAGEM DE SUCESSO
                  E REDIRECIONAR O USUÁRIO PARA A PÁGINA DE CONSULTA*/
                if (res.codigo == 1) {
                    alert(res.mensagem);
                    this.router.navigate(['/consulta-pessoa']);
                }
                else {
                    /*ESSA MENSAGEM VAI SER MOSTRADA CASO OCORRA ALGUMA EXCEPTION
                    NO SERVIDOR (CODIGO = 0)*/
                    alert(res.mensagem);
                }
            },
                (erro) => {
                    /**AQUI VAMOS MOSTRAR OS ERROS NÃO TRATADOS
                     EXEMPLO: SE APLICAÇÃO NÃO CONSEGUIR FAZER UMA REQUEST NA API                        */
                    alert(erro);
                });
        }
    }

    resetPessoa() {
        this.assetCadastroForm.controls.pessoaCodigo.reset();
        this.assetCadastroForm.controls.pessoaNome.reset();
        this.assetCadastroForm.controls.registroAtivo.reset();
    }

    ngOnDestroy(): void {
        this.destroy$.next();
        this.destroy$.complete()
    }
}