import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditArtist } from './edit-artist';

describe('EditArtist', () => {
  let component: EditArtist;
  let fixture: ComponentFixture<EditArtist>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditArtist]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditArtist);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
