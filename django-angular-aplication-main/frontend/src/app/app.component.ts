import { Component } from '@angular/core';
import { ApiService } from './sevices/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'members-front';

  selectedMember = {id:0,name:""};
  members = [
    {name:"member_01",id: 1,surname:"bbb",photo:"http://www.ciclano.com/photo",phone:""},
    {name:"member_02",id: 2,surname:"aaa",photo:"http://www.ciclano.com/photo",phone:""},
    {name:"member_03",id: 3,surname:"ccc",photo:"http://www.ciclano.com/photo",phone:""}
  ]

  constructor(private api:ApiService,private router:Router){
    this.getMembers();
  }

  getMembers = () => {
    this.api.getAllMembers().subscribe(
      data => {
        this.members = data
      },
      error => {
        console.log("Erro",error)
      }
    )
  }

  memberClicked = (member:any) => {
    this.router.navigate(["member-detail",member.id])
  }


}
