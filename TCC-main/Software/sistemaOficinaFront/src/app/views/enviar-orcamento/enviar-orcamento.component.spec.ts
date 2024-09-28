import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnviarOrcamentoComponent } from './enviar-orcamento.component';

describe('EnviarOrcamentoComponent', () => {
  let component: EnviarOrcamentoComponent;
  let fixture: ComponentFixture<EnviarOrcamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnviarOrcamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnviarOrcamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
