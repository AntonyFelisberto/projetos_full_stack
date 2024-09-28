import { Cliente } from "../Cliente/Cliente";
import { MateriaisCliente } from "../Materiais/MateriaisCliente";
import { MateriaisUsuario } from "../Materiais/MateriaisUsuario";


export interface Orcamento{
    cliente:Cliente,
    materiaisCliente:MateriaisCliente[],
    materiaisUsuario:MateriaisUsuario[],
    numeroOrcamento:number,
    valorTotal:number
}