import { TestBed } from '@angular/core/testing';

import { ArtistContactService } from './artist-contact-service';

describe('ArtistContactService', () => {
  let service: ArtistContactService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArtistContactService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
