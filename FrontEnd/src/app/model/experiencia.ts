export class Experiencia {
    id?: number;
    nombreE!: string;
    descripcionE!: string;

    coonstructor(nombreE: string, descripcionE: string,){
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }
}
