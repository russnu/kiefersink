import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditOffering } from './edit-offering';

describe('EditOffering', () => {
  let component: EditOffering;
  let fixture: ComponentFixture<EditOffering>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditOffering]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditOffering);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
