import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Technical } from 'src/app/models/Technicals';
import { ClientService } from 'src/app/services/clientService/client-service';


@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {


  ELEMENT_DATA: Technical[] = []

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private clientService:ClientService) { }

  ngOnInit(): void {
    this.findAll()
  }

  displayedColumns: string[] = ['personId', 'name', 'cpf', 'email','actions'];
  dataSource = new MatTableDataSource<Technical>(this.ELEMENT_DATA);

  findAll(){
    this.clientService.findAll().subscribe(
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
