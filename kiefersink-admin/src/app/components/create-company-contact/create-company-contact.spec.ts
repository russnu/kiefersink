import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCompanyContact } from './create-company-contact';

describe('CreateCompanyContact', () => {
  let component: CreateCompanyContact;
  let fixture: ComponentFixture<CreateCompanyContact>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCompanyContact]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCompanyContact);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
