import { TestBed } from '@angular/core/testing';

import { LivroService } from './livro.service'; // Altere o import para refletir a alteração

describe('LivroService', () => {
  let service: LivroService; // Altere o tipo para refletir a alteração

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LivroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
