import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Call } from 'src/app/models/Call';
import { CallService } from 'src/app/services/callService/call-service';

@Component({
  selector: 'app-chamado-list',
  templateUrl: './chamado-list.component.html',
  styleUrls: ['./chamado-list.component.css']
})
export class ChamadoListComponent implements OnInit {

  ELEMENT_DATA: Call[] = []
  FILTERED_DATA: Call[] = []

  @ViewChild(MatPaginator) paginator: MatPaginator;

  displayedColumns: string[] = ["id","title","nameClient","nameTechnical","dateOpening","priority","status","Ações"];
  dataSource = new MatTableDataSource<Call>(this.ELEMENT_DATA);

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor(private callService:CallService) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll(){
    this.callService.findAll().subscribe(response =>{
      this.ELEMENT_DATA = response
      this.dataSource = new MatTableDataSource<Call>(response);
      this.dataSource.paginator = this.paginator;
    })
  }

  retornaStatus(status:any):string{
    if(status == "0"){
      return "ABERTO";
    }else if(status == "1"){
      return "EM ANDAMENTO";
    }else{
      return "ENCERRADO";
    }
  }

  retornaPrioridade(prioridade:any):string{
    if(prioridade == "0"){
      return "BAIXA";
    }else if(prioridade == "1"){
      return "MÉDIA";
    }else{
      return "ALTA";
    }
  }

  orderByStatus(status:any):void{
    let list: Call[] = []
    this.ELEMENT_DATA.forEach(element=>{
      if(element.status==status){
        list.push(element)
      }
    })
    this.FILTERED_DATA = list
    this.dataSource = new MatTableDataSource<Call>(this.FILTERED_DATA);
    this.dataSource.paginator = this.paginator;
  }

}
