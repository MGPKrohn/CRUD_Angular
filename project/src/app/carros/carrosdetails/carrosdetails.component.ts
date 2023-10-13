import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Carro } from 'src/app/model/carro.model';
import { CarroService } from 'src/app/services/carro.service';

@Component({
  selector: 'app-carrosdetails',
  templateUrl: './carrosdetails.component.html',
  styleUrls: ['./carrosdetails.component.scss']
})
export class CarrosdetailsComponent {
  route = inject(ActivatedRoute);
  logData: any;

  @Input() carro: Carro = new Carro();
  
  @Output() retorno = new EventEmitter<string>();

  carroService = inject(CarroService);

  constructor(){
   
  }
  salvar() {
    if (this.carro.id > 0) {
      this.carroService.update(this.carro.id, this.carro).subscribe({
        next: livro => { 
          this.retorno.emit("Alterado com sucesso!");
        },
        error: erro => { 
          alert('Observe o erro no console!');
          console.error(erro);
        }
      });
    } else {
      this.carroService.save(this.carro).subscribe({
        next: livro => { 
          this.retorno.emit("Salvo com sucesso!");
        },
        error: erro => { 
          alert(' Observe o erro no console!');
          console.error(erro);
        }
      });
    }
  }
}
