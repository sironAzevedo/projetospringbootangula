import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Pessoa } from '../../../blog-model/pessoa-model/pessoa';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EMAIL_SISTEMA } from '../../../blog-constants/blog.constants';
import { PessoaService } from '../../../services/pessoa.service';
import { Mensagem, Destinatario } from '../../../blog-model/mensagem-model/mensagem-model';
import { Response } from '../../../services/response';

@Component({
  selector: 'app-dialog-blog-email',
  templateUrl: './dialog-blog-email.component.html',
  styleUrls: ['./dialog-blog-email.component.scss']
})
export class DialogBlogEmailComponent implements OnInit {

  private pessoas: Pessoa[] = [];
  emailForm: FormGroup;
  @Output() onCloseDialog = new EventEmitter();
  destinatarios: Destinatario[] = [];
  isSending: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<DialogBlogEmailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private pessoaService: PessoaService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.emailForm = this.formBuilder.group({
      emailRemetente: ['', Validators.email],
      emailDestinatario: [''],
      emailMensagem: ['']
    });

    this.emailForm.controls['emailRemetente'].setValue(EMAIL_SISTEMA);
    this.getListPessoa();
  }


  getListPessoa() {
    this.data.pessoaSelecionada.forEach((pessoa: Pessoa) => {
      this.pessoas.push(pessoa);
    });
  }

  sendEmail() {

    this.isSending = true;
    const formValue = this.emailForm.value;
    const type: string = this.data.type;
    this.data.pessoaSelecionada.forEach((pessoa: Pessoa) => {
      const destinatario = {
        nome: pessoa.nome,
        email: pessoa.email,
      } as Destinatario;
      this.destinatarios.push(destinatario);
    });

    const sendMessage: Mensagem = {
      remetente: formValue.emailRemetente,
      destinatarios: this.destinatarios,
      assunto: 'Portal SERV: Registro de Clientes em anexo',
      texto: formValue.emailMensagem,
      html: false,
      anexo: true,
    }
    this.pessoaService.sendEmail(type, sendMessage).subscribe(response => {
      let res: Response = <Response>response;
      if (res.codigo == 1) {
        this.onCloseDialog.emit();
        alert(res.mensagem);
      }
      else {
        alert(res.mensagem);
      }
    });
  }

  cancelEmail() {
    this.onCloseDialog.emit();
  }

}
