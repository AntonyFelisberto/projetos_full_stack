import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Technical } from 'src/app/models/Technicals';
import { TecnicoService } from 'src/app/services/tecnicoService/tecnico.service';

@Component({
  selector: 'app-technical',
  templateUrl: './technical.component.html',
  styleUrls: ['./technical.component.css']
})
export class TechnicalComponent implements OnInit {

  ELEMENT_DATA: Technical[] = []

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private tecnicoService:TecnicoService) { }

  ngOnInit(): void {
    this.findAll()
  }

  displayedColumns: string[] = ['personId', 'name', 'cpf', 'email','actions'];
  dataSource = new MatTableDataSource<Technical>(this.ELEMENT_DATA);

  findAll(){
    this.tecnicoService.findAll().subscribe(
      response =>{ 
        this.ELEMENT_DATA = response;
        this.dataSource = new MatTableDataSource<Technical>(response);
        this.dataSource.paginator = this.paginator;
      })
  }

  applyFilter(event:Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
