import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOffering } from './create-offering';

describe('CreateOffering', () => {
  let component: CreateOffering;
  let fixture: ComponentFixture<CreateOffering>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateOffering]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateOffering);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
