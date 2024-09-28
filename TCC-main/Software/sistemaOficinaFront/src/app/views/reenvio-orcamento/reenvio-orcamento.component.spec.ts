import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReenvioOrcamentoComponent } from './reenvio-orcamento.component';

describe('ReenvioOrcamentoComponent', () => {
  let component: ReenvioOrcamentoComponent;
  let fixture: ComponentFixture<ReenvioOrcamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReenvioOrcamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReenvioOrcamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});