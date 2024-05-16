import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../models/User";

@Injectable({
  providedIn: 'root'
})
export class JwtServiceService {

  constructor(private http: HttpClient) {
  }

  public login(email: string, password: string){
    return this.http.post<{token: string, expiresIn: number}>('http://localhost:8080/auth/login', {
      email: email,
      password: password
    });
  }

  public register(email: string, password: string, username: string){
    return this.http.post('http://localhost:8080/auth/signup', {
      email: email,
      password: password,
      username: username
    });
  }

  public getUsers(){
    return this.http.get<User[]>('http://localhost:8080/api/user/users', {
      headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem('token')
      }
    });
  }

  public getMe(){
    return this.http.get<User>('http://localhost:8080/api/user/me', {
      headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem('token')
      }
    });
  }
}
