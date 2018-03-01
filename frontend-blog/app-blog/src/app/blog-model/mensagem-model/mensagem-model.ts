export class Mensagem {
    remetente: string;
    destinatario: Array<Destinatario>;
    assunto: string;
    texto: string;
    html: boolean;
    anexo: boolean;
}

export class Destinatario {
    nome: string;
    email: string;
} 