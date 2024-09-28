import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { Cliente } from 'src/app/models/Cliente/Cliente';
import { MateriaisUsuario } from 'src/app/models/Materiais/MateriaisUsuario';
import { MaterialOrcamento } from 'src/app/models/Materiais/MaterialClass';
import { MaterialSelection } from 'src/app/models/Materiais/MaterialSelection';
import { Orcamento } from 'src/app/models/Orcamento/Orcamento';
import { AlertsService } from 'src/app/services/alert/alerts.service';
import { MateriaisService } from 'src/app/services/materiaisService/cadastrar-materiais.service';
import { OrcamentoService } from 'src/app/services/Orcamento/orcamento.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-enviar-orcamento',
  templateUrl: './enviar-orcamento.component.html',
  styleUrls: ['./enviar-orcamento.component.scss'],
  providers: [MessageService]
})
export class EnviarOrcamentoComponent implements OnInit {

  materiaisArmazenados: MateriaisUsuario[];
  materiaisParaEscolha: MateriaisUsuario[];
  materiaisSelecionados: MateriaisUsuario[];
  materiaisNoBanco: MateriaisUsuario[];
  draggedProduct: MateriaisUsuario;
  cliente:Cliente;
  numeroOrcamento:number;
  private materiais:MaterialSelection[];
  private orcamentoEnvio:Orcamento;

  nome:FormControl = new FormControl(null,[Validators.minLength(3),Validators.maxLength(255),Validators.required])
  email:FormControl = new FormControl(null,[Validators.minLength(3),Validators.maxLength(255),Validators.email,Validators.required])
  celular:FormControl = new FormControl(null,[Validators.minLength(13),Validators.maxLength(14),Validators.required])
  cidade:FormControl = new FormControl(null,[Validators.minLength(3),Validators.maxLength(50),Validators.required])
  endereco:FormControl = new FormControl(null,[Validators.minLength(3),Validators.maxLength(255),Validators.required])
  estado:FormControl = new FormControl(null,[Validators.minLength(2),Validators.maxLength(50),Validators.required])
  placa:FormControl = new FormControl(null,[Validators.minLength(7),Validators.maxLength(8),Validators.required])
  veiculo:FormControl = new FormControl(null,[Validators.minLength(3),Validators.maxLength(255),Validators.required])
  telefone:FormControl = new FormControl(null,[Validators.minLength(12),Validators.maxLength(15),Validators.required])

  constructor(private alert:AlertsService,
              private materialService:MateriaisService,
              private orcamentoService:OrcamentoService,
              private primengConfig: PrimeNGConfig,
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.iniciarAplicacao();
  }

  iniciarAplicacao(){
    this.numeroOrcamento = 0;
    this.primengConfig.ripple = true;
    this.materiaisArmazenados = [];
    this.materiaisNoBanco = [];
    this.materiaisSelecionados = [];
    this.inicializarVariaveis();
    this.iniciarDadosDeMateriaisUsuario();
    this.carregarMateriaisCliente();
  }

  inicializarVariaveis(){
    this.orcamentoService.trazerIdOrcamento().subscribe(
      (id:number)=>{this.numeroOrcamento = id}
    )

    this.cliente = {
      idCliente: 0,
      nome: '',
      email: '',
      veiculo: '',
      placa: '',
      endereco: '',
      cidade: '',
      estado: '',
      telefone: '',
      celular: ''
    };

    this.materiais = [];

  }

  private iniciarDadosDeMateriaisUsuario(){
    this.materialService.trazerMateriais().subscribe(
      (response:MateriaisUsuario[]) => {
        for(let i=0;i<response.length;i++){
          if(response[i].quantidade>1){
              for(let j=0;j<response[i].quantidade;j++){
                  this.materiaisNoBanco.push({
                      'idMaterial':response[i].idMaterial,
                      'unidade':response[i].unidade,
                      'quantidade':response[i].quantidade,
                      'codProduto':response[i].codProduto,
                      'descriminizacao':response[i].descriminizacao,
                      'precoUnidade':response[i].precoUnidade
                  })
              }
          }else if(response[i].quantidade === 1){
              this.materiaisNoBanco.push({
                  'idMaterial':response[i].idMaterial,
                  'unidade':response[i].unidade,
                  'quantidade':response[i].quantidade,
                  'codProduto':response[i].codProduto,
                  'descriminizacao':response[i].descriminizacao,
                  'precoUnidade':response[i].precoUnidade
              })
          }
        }
        this.materiaisParaEscolha = this.materiaisNoBanco
      }
    )
  }

  validaCliente():boolean{
    return this.celular.valid &&
          this.cidade.valid &&
          this.email.valid &&
          this.endereco.valid &&
          this.nome.valid &&
          this.estado.valid &&
          this.placa.valid &&
          this.veiculo.valid &&
          this.telefone.valid
  }

  /*EVENTO DE MATERIAIS DO CLIENTE */
  adicionarMateriais(){
    this.materiais.push({idMaterial:"",codProduto:"", quantidade:"", unidade: "", descriminizacao:"", precoUnidade:""});
    this.carregarMateriaisCliente();
    this.travarOutraInsercaoDeMateriaisCliente(document.querySelector("#materialContainer > div:last-child"));
  }

  carregarMateriaisCliente(){
    let materiaisContainer = document.querySelector("#materialContainer");
    materiaisContainer.innerHTML = "";
    this.materiais.forEach((dados)=>{
        let idMaterial = dados.idMaterial;
        let quantidade = dados.quantidade;
        let unidade = dados.unidade;
        let codProduto = dados.codProduto;
        let descriminizacao = dados.descriminizacao;
        let precoUnidade = dados.precoUnidade;
        let materialContainer = `<div class="materialDados" data-id="${idMaterial}">

                                      <div class="w3-row w3-section">
                                        <div class="w3-col" style="width:50px"><i class="w3-xxlarge material-icons">plus_one</i></div>
                                          <div class="w3-rest">
                                            <input class="quantidade w3-input w3-border w3-white" type="number" value="${quantidade}"  placeholder="Digite a quantidade">
                                      </div>
                                      <i class="fa fa-thin fa-money-check-dollar-pen"></i>
                                      <div class="w3-row w3-section">
                                        <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-tag"></i></div>
                                          <div class="w3-rest">
                                            <input class="unidade w3-input w3-border w3-white" type="number" value="${unidade}"  placeholder="Digite a unidade">
                                          </div>
                                      </div>

                                      <div class="w3-row w3-section">
                                        <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-barcode"></i></div>
                                          <div class="w3-rest">
                                            <input class="codProduto w3-input w3-border w3-white" type="number" value="${codProduto}"  placeholder="Digite o codProduto">
                                          </div>
                                      </div>

                                      <div class="w3-row w3-section">
                                        <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-dollar"></i></div>
                                          <div class="w3-rest">
                                            <input class="precoUnidade w3-input w3-border w3-white" type="number" value="${precoUnidade}" step=0.01 placeholder="Digite o preço por unidade usando ponto para casas decimais EX:141.67">
                                          </div>
                                      </div>

                                      <div class="w3-row w3-section">
                                        <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-pencil"></i></div>
                                          <div class="w3-rest">
                                            <textarea class="descriminizacao w3-input w3-border w3-white" type="text" placeholder="Digite a descriminizacao" rows="4" cols="50">${descriminizacao}</textarea>
                                          </div>
                                      </div>

                                      <div class="action">
                                        <a class="salvar"><button class="w3-btn w3-blue-grey" class="salvar">Salvar Dados</button></a>
                                        <a class="remover"><button class="w3-btn w3-blue-grey" class="salvar">Remover Dados</button></a>
                                      </div>
                                    </div>`;
        materiaisContainer.innerHTML += materialContainer;
    });
      this.salvarMateriaisCliente();
      this.removerMateriaisCliente();
      this.travarOutraInsercaoDeMateriaisCliente(false);
  }

  salvarMateriaisCliente(){

    document.querySelectorAll("#materialContainer .salvar").forEach((formarDados, i)=>{
      formarDados.addEventListener("click", ()=>{
        let idMaterial = formarDados.parentElement.parentElement.querySelector<HTMLInputElement>("data-id");
        let quantidade = formarDados.parentElement.parentElement.querySelector<HTMLInputElement>(".quantidade").value;
        let unidade = formarDados.parentElement.parentElement.querySelector<HTMLInputElement>('.unidade').value
        let descriminizacao = formarDados.parentElement.parentElement.querySelector<HTMLInputElement>(".descriminizacao").value;
        let precoUnidade = formarDados.parentElement.parentElement.querySelector<HTMLInputElement>(".precoUnidade").value;
        let codProduto = formarDados.parentElement.parentElement.querySelector<HTMLInputElement>(".codProduto").value;
        if(!descriminizacao.length || !precoUnidade.length || !unidade.length || !quantidade.length || !codProduto.length){
          this.alert.info("Campos precisam ser preenchidos para salvar","INFO");
        }else{
          this.materiais.splice(i, 1, {idMaterial: idMaterial,codProduto:codProduto, quantidade: quantidade, unidade:unidade, descriminizacao: descriminizacao, precoUnidade:precoUnidade});
          this.messageService.add({severity:'success', summary: "", detail: "Material salvo com sucesso"});
          this.carregarMateriaisCliente();
          this.travarOutraInsercaoDeMateriaisCliente(false);
        }
        return true;
      });
    });
  }

  removerMateriaisCliente(){
    document.querySelectorAll("#materialContainer .remover").forEach((el, i)=>{
      el.addEventListener("click", ()=>{
        this.messageService.add({severity:'success', summary: "", detail: "Material deletado com sucesso"});
        this.materiais.splice(i, 1);
        this.carregarMateriaisCliente();
      });
    });
  }

  private travarOutraInsercaoDeMateriaisCliente(element){
    if(element == false){
      document.querySelectorAll("#materiais button, #materiais .container > div").forEach((bloquear)=>{
        bloquear.classList.remove("disabled");
      });
      return false;
    }
    document.querySelectorAll("#materiais button, #materiais .container > div").forEach((bloquear)=>{
      if(bloquear != element){
        bloquear.classList.add("disabled");
      }
    });
    return true;
  }

  reiniciarMateriaisCliente(){
    for(let totalMateriaisCliente = 0; totalMateriaisCliente<this.materiais.length;totalMateriaisCliente++){
      this.materiais.splice(totalMateriaisCliente, 1);
      this.carregarMateriaisCliente();
    }
  }

  validarMateriais():boolean{
    for(const verificarSeTemDadosNull of this.materiais){
      if(
        (verificarSeTemDadosNull.codProduto == null || verificarSeTemDadosNull.codProduto == "") ||
        (verificarSeTemDadosNull.descriminizacao == null || verificarSeTemDadosNull.descriminizacao == "")  ||
        (verificarSeTemDadosNull.precoUnidade == null || verificarSeTemDadosNull.precoUnidade == "") ||
        (verificarSeTemDadosNull.quantidade == null || verificarSeTemDadosNull.quantidade == "") ||
        (verificarSeTemDadosNull.unidade == null || verificarSeTemDadosNull.unidade == "")){
          return false
      }
    }
    return true;
  }

  /*ENVIAR PARA O BACK*/
  validarDadosDoHTMLParaEnviarDados(){
    var validarDadosMateriais = this.validarMateriais();
    if(this.materiaisSelecionados.length>0 && this.materiais.length>0 && validarDadosMateriais){
        this.enviarDados();
    }else if(this.materiais.length>0 && validarDadosMateriais){
      this.enviarDados();
    }else if(this.materiaisSelecionados.length>0 && this.materiais.length === 0){
      this.enviarDados();
    }else{
      if(this.materiais.length === 0){
        this.alert.info("preencher materiais de cliente e/ou selecionar materiais no campo de seleção","ATENÇÃO");
      }else if(!validarDadosMateriais){
        this.alert.info("material de cliente possui dados vazios, por faver preencha e salve todos os campos ou exclua eles","ATENÇÃO");
      }else if(this.materiaisSelecionados.length === 0){
        this.alert.info("selecionar materiais no campo de seleção e/ou preencher materiais de cliente","ATENÇÃO");
      }
    }
  }

  enviarDados(){
    
        const materialClass = new MaterialOrcamento(this.materiais,this.materiaisSelecionados)
        
        this.orcamentoEnvio = {
          'cliente':this.cliente,
          'materiaisUsuario':this.materiaisSelecionados,
          'materiaisCliente':materialClass.getMaterial,
          'numeroOrcamento':this.numeroOrcamento,
          'valorTotal':materialClass.getValorTotal,
        }

        var total= Number(materialClass.getValorTotal)

        Swal.fire({
          title: 'Quer enviar o e-mail?',
          text: "O total do orçamento deu :"+total+", deseja continuar ?",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Sim, enviar!'
        }).then((result) => {
          if (result.isConfirmed) {
            this.orcamentoService.enviarEmailCliente(this.orcamentoEnvio).then(
              response => {
                this.alert.success(response.toString(),"SUCESSO")
                this.reiniciarMateriaisCliente();
                this.iniciarAplicacao();
              },exception=>{
                if(exception.error.error){
                  exception.error.error.forEach(element => {
                    this.messageService.add({severity:'error', summary: element.fieldName, detail: element.message});
                  });
                }else{
                  this.messageService.add({severity:'error', summary: 'Erro', detail: exception.error.message});
                }
              }
            )
          }else{
            this.alert.info("dados não enviados, revise suas informações","INFO");
          }
        })
  }

}
