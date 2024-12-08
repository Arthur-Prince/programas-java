import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiserviceService {

  constructor(
    private http: HttpClient
  ) { }

  readonly url: string = 'api/'
  // readonly url: string = 'https://trabalho-ia/api'

  getTreinamento(): Observable<any> {
    return this.http.get<any>(`${this.url}treinamento`)
  }
  getTreinamento1(): Observable<any> {
    return this.http.get<any>(`${this.url}treinamento1`)
  }
  getPesos() {
    return this.http.get<any>(`${this.url}getPesos`)
  }
  getPesosAleatorios(): Observable<any> {
    return this.http.get<any>(`${this.url}PesosAleatorios`)
  }
  getPesosPerfeitosIniciais(): Observable<any> {
    return this.http.get<any>(`${this.url}PesosPerfeitosIniciais`)
  }
  getPesosPerfeitosFinais(): Observable<any> {
    return this.http.get<any>(`${this.url}pesosPerfeitosFinais`)
  }
  getMudarProblema(funcao: string): Observable<any> {
    const header = new HttpHeaders()
      .set('Content-Type', 'application/jason')
      .set('Accept', 'application/jason')
      .set('Access-Control-Allow-Origin', '*');
    return this.http.get(`${this.url}problema/${funcao}` )
  }
  post(textfuncao: string): Observable<any> {
    const header = new HttpHeaders()
    .set('Content-Type', 'application/jason')
    .set('Accept', 'application/jason')
    .set('Access-Control-Allow-Origin', '*');
  return this.http.post(`${this.url}`, textfuncao,{'headers': header} )
  }
}

