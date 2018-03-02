import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { Router } from '@angular/router';
import { PessoaService } from '../../services/pessoa.service';
import { Pessoa } from '../../blog-model/pessoa-model/pessoa';
import { Response } from '../../services/response';
import { MatPaginator, MatSort, MatTableDataSource, MatDialogRef, MatDialog } from '@angular/material';
import { sortByProperty } from '../../../validators/sort-by';
import { FILE_TYPE_PDF, FILE_TYPE_EXCEL } from '../../blog-constants/blog.constants';
import { DialogBlogEmailComponent } from '../../shered/components/dialog-blog-email/dialog-blog-email.component';
import { SelectionModel } from '@angular/cdk/collections';
import { EventEmitter } from 'events';


@Component({
    selector: 'app-consulta-pessoa',
    templateUrl: './consulta.component.html',
    styleUrls: ["./consulta.component.scss"]
})
export class ConsultaComponent implements OnInit {

    private pessoas: Pessoa[] = [];
    private titulo: string;
    displayedColumns = ['select', 'codigo', 'nome', 'email', 'ativo', 'editar', 'excluir'];
    dataSource: MatTableDataSource<Pessoa>;
    selection = new SelectionModel<Pessoa>(true, []);
    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort; 

    formDialogRef: MatDialogRef<DialogBlogEmailComponent> | null = null;

    constructor(
        private pessoaService: PessoaService,
        private router: Router,
        public dialog: MatDialog
    ) { }

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

    /**EXCLUI ITENS SELECIONADOS*/
    excluirSelecionados() {
        this.selection.selected.forEach((pessoa: Pessoa) => {
            this.pessoaService.excluirPessoa(pessoa.codigo).subscribe(response => {
                this.getPessoa();
            },
                (erro) => {
                    alert(erro);
                });
        });
    }

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
        this.dataSource.filter = filterValue;
    }

    editar(codigo: number): void {
        this.router.navigate(['/cadastro-pessoa', codigo]);
    }

    downloadFile() {
        this.pessoaService.getDownloadFile();
    }

    sendEmailPdf() {
        this.openDialog(FILE_TYPE_PDF);
    }

    sendEmailExcel() {
        this.openDialog(FILE_TYPE_EXCEL);
    }

    openDialog(type: string) {
        this.formDialogRef = this.dialog.open(DialogBlogEmailComponent, {
            data: { type: type, pessoaSelecionada: this.selection.selected }
        });

        this.formDialogRef.componentInstance.onCloseDialog.subscribe(() => {
            this.closeFormDialog();
        });
    }

    /** Whether the number of selected elements matches the total number of rows. */
    isAllSelected() {
        const numSelected = this.selection.selected.length;
        const numRows = this.dataSource.data.length;
        return numSelected === numRows;
    }

    /** Selects all rows if they are not all selected; otherwise clear selection. */
    masterToggle() {
        this.isAllSelected() ?
            this.selection.clear() :
            this.dataSource.data.forEach(row => this.selection.select(row));
    }

    closeFormDialog() {
        if (this.formDialogRef) {
            this.formDialogRef.close();
            this.formDialogRef = null;
        }
    }
}