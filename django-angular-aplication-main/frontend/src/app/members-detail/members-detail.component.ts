import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ApiService } from '../sevices/api.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-members-detail',
  templateUrl: './members-detail.component.html',
  styleUrls: ['./members-detail.component.css']
})
export class MembersDetailComponent {

  constructor(private route:ActivatedRoute,private api:ApiService,private router:Router,private appComponent:AppComponent){}

  selectedMember={id:0,name:"",surname:"",phone:"",photo:""};
  selectedId?:number;
  
  ngOnInit(){
    this.route.paramMap.subscribe((param:ParamMap)=>{
      const paramsId = param.get('id')
      if (paramsId != null){
        let id = parseInt(paramsId)
        this.selectedId = id
        this.loadMembers(id)
      }
    })
    
  }

  loadMembers(id:number){
    if (id != null){
      this.api.getMember(id).subscribe(
        data => {
          this.selectedMember = data
        },
        error => {
          console.log("Erro",error)
        }
      )
    }
  }

  update(){
    this.api.updateMember(this.selectedMember).subscribe(
      data => {
        this.selectedMember = data
      },
      error => {
        console.log("Erro",error)
      }
    )
  }

  delete(){
    if (this.selectedId != null){
      this.api.deleteMember(this.selectedId).subscribe(
        data => {
          let index = 0;
          this.appComponent.members.forEach((e,i) =>{
            if(e.id == this.selectedId){
              index = i;
            }
          })
          this.appComponent.members.splice(index,1)
        },
        error => {
          console.log("Erro",error)
        }
      )
    }
  }

  newMember(){
    this.router.navigate(["new-member"])
  }

}