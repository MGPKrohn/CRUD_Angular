import { TestBed } from '@angular/core/testing';

import { CarroService } from './carro.service'; // Altere o import para refletir a alteração

describe('CarroService', () => {
  let service: CarroService; // Altere o tipo para refletir a alteração

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
