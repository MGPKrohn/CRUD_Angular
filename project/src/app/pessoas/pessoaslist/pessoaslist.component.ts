import { Input } from '@angular/core';
import { Component, inject} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Pessoa } from 'src/app/model/pessoa.model';
import { PessoaService } from 'src/app/services/pessoa.service';

@Component({
  selector: 'app-pessoaslist',
  templateUrl: './pessoaslist.component.html',
  styleUrls: ['./pessoaslist.component.scss']
})
export class PessoaslistComponent {
  modalService = inject(NgbModal);

  pessoaSelecionadaParaEdicao: Pessoa = new Pessoa();
  indiceSelecionadoParaEdicao!: number;

  pessoa: Pessoa[] = [];

  pessoaService = inject(PessoaService);

  exibirIdade: 'todas' | 'menor' | 'maior' = 'todas';

  constructor(){
    this.listAll();
  }

  listAll() {

    this.pessoaService.listAll().subscribe({
      next: lista => { // QUANDO DÁ CERTO
        this.pessoa = lista;
      },
      error: erro => { // QUANDO DÁ ERRO
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });

  }

  exemploErro() {

    this.pessoaService.exemploErro().subscribe({
      next: lista => { // QUANDO DÁ CERTO
        this.pessoa = lista;
      },
      error: erro => { // QUANDO DÁ ERRO
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });

  }


  alterarCondicaoExibicao(condicao: 'todas' | 'menor' | 'maior'): void {
    this.exibirIdade = condicao;
  }

  abrirModal(content: any) {
    this.modalService.open(content, { size: 'lg' });
  }

  abrirModalEditar(content: any, pessoa: Pessoa) {
    this.pessoaSelecionadaParaEdicao = Object.assign({},pessoa);

    this.modalService.open(content, { size: 'lg' });
  }

  atualizarLista(mensagem: string){
    alert(mensagem);
    this.modalService.dismissAll();
    this.listAll();
  }

  deletar(id: number) {
    this.pessoaService.delete(id).subscribe(() => this.listAll());
    alert("Deletado com sucesso!");
    this.listAll();
  }
}
