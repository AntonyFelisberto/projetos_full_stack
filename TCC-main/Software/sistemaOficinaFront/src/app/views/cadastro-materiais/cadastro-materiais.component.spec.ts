import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroMateriaisComponent } from './cadastro-materiais.component';

describe('CadastroMateriaisComponent', () => {
  let component: CadastroMateriaisComponent;
  let fixture: ComponentFixture<CadastroMateriaisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroMateriaisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastroMateriaisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});