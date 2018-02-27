import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { PessoaService } from '../../services/pessoa.service';
import { Pessoa } from '../../services/pessoa';
import { Response } from '../../services/response';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { sortByProperty } from '../../../validators/sort-by';

@Component({
    selector: 'app-consulta-pessoa',
    templateUrl: './consulta.component.html',
    styleUrls: ["./consulta.component.scss"]
})
export class ConsultaComponent implements OnInit {

    private pessoas: Pessoa[] = [];
    private titulo: string;
    displayedColumns = ['codigo', 'nome', 'ativo', 'editar', 'excluir'];
    dataSource: MatTableDataSource<Pessoa>;
    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    constructor(private pessoaService: PessoaService,
        private router: Router) { }

    ngOnInit() {

        /*SETA O TÍTULO */
        this.titulo = "Registros Cadastrados";
        this.getPessoa();
    }

    getPessoa() {
        /*CHAMA O SERVIÇO E RETORNA TODAS AS PESSOAS CADASTRADAS */
        /*  this.pessoaService.getPessoas().subscribe(res => this.pessoas = res); */
        this.pessoaService.getPessoas().subscribe(results => {
            if (!results) {
                return;
            }
            this.pessoas = results;
            this.dataSource = new MatTableDataSource(this.pessoas);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
        })

    };

    /**EXCLUI UM REGISTRO QUANDO CLICAMOS NA OPÇÃO EXCLUIR DE UMA 
     * LINHA DA TABELA*/
    excluir(codigo: number, index: number): void {

        if (confirm("Deseja realmente excluir esse registro?")) {

            /*CHAMA O SERVIÇO PARA REALIZAR A EXCLUSÃO */
            this.pessoaService.excluirPessoa(codigo).subscribe(response => {

                this.getPessoa();
                /**PEGA O RESPONSE DO SERVIÇO */
                let res: Response = <Response>response;

                /*1 = SUCESSO
                * MOSTRAMOS A MENSAGEM RETORNADA PELO SERVIÇO E DEPOIS REMOVEMOS
                O REGISTRO DA TABELA HTML*/
                if (res.codigo == 1) {
                    alert(res.mensagem);
                    this.pessoas.splice(index, 1);
                }
                else {
                    /*0 = EXCEPTION GERADA NO SERVIÇO JAVA */
                    alert(res.mensagem);
                }
            },
                (erro) => {
                    /*MOSTRA ERROS NÃO TRATADOS */
                    alert(erro);
                });
        }

    }

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
        this.dataSource.filter = filterValue;
    }

    editar(codigo: number): void {

        this.router.navigate(['/cadastro-pessoa', codigo]);

    }

    downloadFile(){
        this.pessoaService.getFilePessoa();
    }

}