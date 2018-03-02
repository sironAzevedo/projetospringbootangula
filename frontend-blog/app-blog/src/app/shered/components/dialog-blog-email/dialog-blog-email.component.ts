import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Pessoa } from '../../../blog-model/pessoa-model/pessoa';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EMAIL_SISTEMA } from '../../../blog-constants/blog.constants';

@Component({
  selector: 'app-dialog-blog-email',
  templateUrl: './dialog-blog-email.component.html',
  styleUrls: ['./dialog-blog-email.component.scss']
})
export class DialogBlogEmailComponent implements OnInit {

  private pessoas: Pessoa[] = [];
  emailForm: FormGroup;
  @Output() onCloseDialog = new EventEmitter();

  constructor(
    public dialogRef: MatDialogRef<DialogBlogEmailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.emailForm = this.formBuilder.group({
      emailRemetente: ['', Validators.email],
      emailDestinatario: ['', Validators.compose([Validators.required])],
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

  }

  cancelEmail() {
    this.onCloseDialog.emit();
  }

}
