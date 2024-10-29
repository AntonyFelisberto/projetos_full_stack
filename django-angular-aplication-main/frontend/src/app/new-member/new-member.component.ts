import { Component } from '@angular/core';
import { ApiService } from '../sevices/api.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-new-member',
  templateUrl: './new-member.component.html',
  styleUrls: ['./new-member.component.css']
})
export class NewMemberComponent {

  member = {id:0,name:"",surname:"",phone:"",photo:""};

  constructor(private api:ApiService,private appComponent:AppComponent){}

  ngOnInit(){

  }

  save(){
    this.api.saveMember(this.member).subscribe(
      data => {
        this.appComponent.members.push(data)
      },
      error => {
        console.log("Erro",error)
      }
    )
  }
}
