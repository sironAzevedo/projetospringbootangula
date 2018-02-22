import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material';
import { TextLengthPipe } from './pipes/text-length.pipe';
import { MaterialModule } from '../+shered/angular.module';

@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        MaterialModule
    ],
    exports: [
        FormsModule,
        CommonModule,
        ReactiveFormsModule,
        TextLengthPipe,
        MaterialModule
    ],
    declarations: [
        TextLengthPipe
    ],
    entryComponents: [

    ],
})
export class SharedModule { }