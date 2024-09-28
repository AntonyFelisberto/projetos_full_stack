import { TestBed } from '@angular/core/testing';
import { MateriaisService } from './cadastrar-materiais.service';


describe('CadastrarMateriaisService', () => {
  let service: MateriaisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MateriaisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
