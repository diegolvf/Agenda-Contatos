import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrupoInfo } from './grupo-info';

describe('GrupoInfo', () => {
  let component: GrupoInfo;
  let fixture: ComponentFixture<GrupoInfo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GrupoInfo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrupoInfo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
