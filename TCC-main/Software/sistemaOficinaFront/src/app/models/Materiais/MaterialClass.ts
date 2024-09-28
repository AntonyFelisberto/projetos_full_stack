import { MateriaisCliente } from "./MateriaisCliente"
import { MateriaisUsuario } from "./MateriaisUsuario";
import { MaterialSelection } from "./MaterialSelection"

export class MaterialOrcamento{

    material:MateriaisCliente[] = []
    valorTotal:number = 0

    constructor(materiais:MaterialSelection[],dadosDeMaterialParaSoma:MateriaisUsuario[]){
        for(let i =0;i<materiais.length;i++){
            this.material.push({
                idMaterial:materiais[i].idMaterial,
                codProduto:parseInt(materiais[i].codProduto),
                quantidade:parseInt(materiais[i].quantidade),
                unidade:parseInt(materiais[i].unidade),
                descriminizacao:materiais[i].descriminizacao,
                precoUnidade:parseFloat(materiais[i].precoUnidade),
            });
            if(materiais[i].precoUnidade !== null){
                this.valorTotal = this.valorTotal + parseFloat(materiais[i].precoUnidade); 
            }
        }
        for(let i =0;i<dadosDeMaterialParaSoma.length;i++){
            if(dadosDeMaterialParaSoma[i].precoUnidade !== null){
                this.valorTotal = this.valorTotal + dadosDeMaterialParaSoma[i].precoUnidade; 
            }         
        }
    }

    get getMaterial(){
        return this.material
    }

    get getValorTotal(){
        return this.valorTotal;
    }

}
