import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Offerings } from './offerings';

describe('Offerings', () => {
  let component: Offerings;
  let fixture: ComponentFixture<Offerings>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Offerings]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Offerings);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
