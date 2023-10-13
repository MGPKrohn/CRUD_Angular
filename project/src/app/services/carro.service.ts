import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Carro } from '../model/carro.model'; // Certifique-se de importar a classe Carro correta

@Injectable({
  providedIn: 'root'
})
export class CarroService {

  API: string = 'http://localhost:8080/api/carro'; // Altere o caminho da API para refletir a entidade Carro
  http = inject(HttpClient);

  constructor() { }

  listAll(): Observable<Carro[]> {
    return this.http.get<Carro[]>(this.API);
  }

  save(carro: Carro): Observable<Carro> {
    return this.http.post<Carro>(this.API, carro);
  }

  update(id: number, carro: Carro): Observable<Carro> {
    return this.http.put<Carro>(`${this.API}/${id}`, carro);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }

  exemploErro(): Observable<Carro[]> {
    return this.http.get<Carro[]>(this.API + '/erro');
  }

  /*
  CASO PRECISE ENVIAR REQUEST PARAMS, BASTA DECLARAR ASSIM E INCLUIR NA REQUISIÇÃO HTTP

  let params = new HttpParams()
      .set('empresaId', empresaId.toString())

  return this.http.get<Carro[]>(this.API, { params: params});

  SE PRECISAR COLOCAR COISAS NO HEADER DA REQUISIÇÃO

  let headers = new HttpHeaders()
      .set("Content-Type", "application/json");

  return this.http.get<Carro[]>(this.API, { headers: headers});

  */
}
