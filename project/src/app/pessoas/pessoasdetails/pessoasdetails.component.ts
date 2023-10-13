import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Pessoa } from 'src/app/model/pessoa.model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-pessoasdetails',
  templateUrl: './pessoasdetails.component.html',
  styleUrls: ['./pessoasdetails.component.scss']
})
export class PessoasdetailsComponent {
 route = inject(ActivatedRoute);
 logData: any;

//  @Input() pessoa1:Pessoa[] = [];
 
 @Input() pessoa: Pessoa = new Pessoa();

 @Output() retorno = new EventEmitter<string>();
 
 pessoaService = inject(PessoaService);

 constructor(){
    let id = this.route.snapshot.paramMap.get(`id`);

    if(id){
      this.logData = {nome: "Maria", idade: 45}
      console.log("Maria", 45);
    }else{
      this.logData = {nome:"Novo Nome", idade: 0}
      console.log("Novo Nome",0);
    }
 }
 salvar() {
    if(this.pessoa.id > 0){
      this.pessoaService.update(this.pessoa.id, this.pessoa).subscribe({
        next: pessoa => { // QUANDO DÁ CERTO
          this.retorno.emit("Alterado com sucesso!");
        },
        error: erro => { // QUANDO DÁ ERRO
          alert('Exemplo de tratamento de erro/exception! Observe o erro no console!');
          console.error(erro);
        }
        });
    }else{
    this.pessoaService.save(this.pessoa).subscribe({
      next: pessoa => { // QUANDO DÁ CERTO
        this.retorno.emit("Salvo com sucesso!");
      },
      error: erro => { // QUANDO DÁ ERRO
        alert('Exemplo de tratamento de erro/exception! Observe o erro no console!');
        console.error(erro);
      }
      });
    }
  }
}
