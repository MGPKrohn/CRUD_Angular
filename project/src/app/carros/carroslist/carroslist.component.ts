import { Component, inject } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Carro } from 'src/app/model/carro.model';
import { CarroService } from 'src/app/services/carro.service';

@Component({
  selector: 'app-carroslist',
  templateUrl: './carroslist.component.html',
  styleUrls: ['./carroslist.component.scss']
})
export class CarroslistComponent {
  modalSerivce = inject(NgbModal);

  carroSelecionadoParaEdicao: Carro = new Carro();
  indiceSelecionadoParaEdicao!: number;

  carro: Carro [] = [];

  carroService = inject(CarroService);

  constructor(){
    this.listAll();
  }
  listAll() {

    this.carroService.listAll().subscribe({
      next: lista => { // QUANDO DÁ CERTO
        this.carro = lista;
      },
      error: erro => { // QUANDO DÁ ERRO
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });
  }

  exemploErro() {

    this.carroService.exemploErro().subscribe({
      next: lista => { // QUANDO DÁ CERTO
        this.carro = lista;
      },
      error: erro => { // QUANDO DÁ ERRO
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });

  }

  abrirModalCarro(content: any) {
    this.modalSerivce.open(content, { size: 'lg' });
  }
  abrirModalEditar(content: any, carro: Carro) {
    this.carroSelecionadoParaEdicao = Object.assign({},carro);

    this.modalSerivce.open(content, { size: 'lg' });
  }

  atualizarLista(mensagem: string){
    alert(mensagem);
    this.modalSerivce.dismissAll();
    this.listAll();
  }

  deletar(id: number) {
    this.carroService.delete(id).subscribe(() => this.listAll());
    alert("Deletado com sucesso!");
    this.listAll();
  }
}
