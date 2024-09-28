import { TestBed } from '@angular/core/testing';

import { AutenticationRouteGuard } from './autentication-route.guard';

describe('AutenticationRouteGuard', () => {
  let guard: AutenticationRouteGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AutenticationRouteGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
