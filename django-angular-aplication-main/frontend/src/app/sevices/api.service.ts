import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ApiService {



  private baseUrlDjango = 'http://127.0.0.1:8000'
  token = "Token 0a4d297db6c31f7482a6e6fc53ce5a111a2e8ae0"
  private httpHeaders = new HttpHeaders().set("content-type","application/json").set("Authorization",this.token)

  constructor(private http:HttpClient) { }

  getAllMembers(): Observable<any>{
    return this.http.get(this.baseUrlDjango + "/members/", 
      {headers : this.httpHeaders}
    )
  }

  getMember(id:number | string): Observable<any>{
    return this.http.get(this.baseUrlDjango + "/members/" + id + "/", 
      {headers : this.httpHeaders}
    )
  }

  updateMember(member:any): Observable<any>{
    let body = {name:member.name,surname:member.surname,phone:member.phone}
    return this.http.put(this.baseUrlDjango + "/members/" + member.id + "/", body,
      {headers : this.httpHeaders}
    )
  }

  saveMember(member: any): Observable<any>{
    let body = {name:member.name,surname:member.surname,phone:member.phone}
    return this.http.post(this.baseUrlDjango + "/members/", body,
      {headers : this.httpHeaders}
    )
  }

  deleteMember(id:number | string) : Observable<any>{
    return this.http.delete(this.baseUrlDjango + "/members/" + id + "/", 
      {headers : this.httpHeaders}
    )
  }
  
}
