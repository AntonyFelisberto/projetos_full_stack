import { ComponentFixture, TestBed } from '@angular/core/testing';
import { VisualizacaoMateriaisComponent } from './visualizacao-materiais.component';

describe('VisualizacaoMateriaisComponent', () => {
  let component: VisualizacaoMateriaisComponent;
  let fixture: ComponentFixture<VisualizacaoMateriaisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizacaoMateriaisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualizacaoMateriaisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});