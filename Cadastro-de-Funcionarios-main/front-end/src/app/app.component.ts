import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Empregado } from './empregado';
import { EmpregadoService } from './empregado.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  public empregado!: Empregado[];
  public editarEmpregado!: Empregado;
  public deletarEmpregado!:Empregado;

  constructor(private empregadoService:EmpregadoService){}
 
  ngOnInit() {
    this.getEmpregados();
  }

  public getEmpregados(): void {
    this.empregadoService.getEmpregados().subscribe(
      (response: Empregado[]) => {
          this.empregado = response;
      },
      (error:HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }


  public onDeleteEmpregados(idEmpregado: number){
    this.empregadoService.deleteEmpregados(idEmpregado).subscribe(
      (response:void) => {
        console.log(response)
        this.getEmpregados();
      },(error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public onAddEmpregados(addForm: NgForm){
    document.getElementById('add-employee-form')?.click();
    this.empregadoService.addEmpregados(addForm.value).subscribe(
      (response:Empregado) => {
        console.log(response);
        this.getEmpregados();
        addForm.reset();
      },(error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public onUpdEmpregados(empregado: Empregado){
    this.empregadoService.updateEmpregados(empregado).subscribe(
      (response:Empregado) => {
        console.log(response);
        this.getEmpregados();
      },(error: HttpErrorResponse) => {
        alert(error.message)
      }
    );
  }

  public searchEmpregado(chave: string){
    const resultados:Empregado[] = [];

    for(const listaDeEmpregados of this.empregado){
      if(listaDeEmpregados.nome.toLowerCase().indexOf(chave.toLowerCase()) !== -1
      || listaDeEmpregados.email.toLowerCase().indexOf(chave.toLowerCase()) !== -1
      || listaDeEmpregados.telefone.toLowerCase().indexOf(chave.toLowerCase()) !== -1
      || listaDeEmpregados.trabalho.toLowerCase().indexOf(chave.toLowerCase()) !== -1){
        resultados.push(listaDeEmpregados);
      }
    }
    this.empregado = resultados;

    if(resultados.length === 0 || !chave){
      this.getEmpregados();
    }
  }

  public addEmpregado(){
    const button = document.createElement('button');
    const container = document.getElementById('main-container');

    button.type = 'button';
    button.style.display = 'none';
    
    button.setAttribute('data-toggle','modal');
    button.setAttribute('data-target','#addEmpregadoModal');
    
    container?.appendChild(button);
    button.click();
  }

  public onOpenModal(empregado: Empregado, mode: string): void{
    const button = document.createElement('button');
    const container = document.getElementById('main-container');


    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle','modal');

    if(mode === 'edit'){
      this.editarEmpregado = empregado;
      button.setAttribute('data-target','#updateEmpregadoModal');
    }
    if(mode === 'delete'){
      this.deletarEmpregado = empregado;
      button.setAttribute('data-target','#deleteEmployeeModal');
    }
    container?.appendChild(button);
    button.click();
  }
}
