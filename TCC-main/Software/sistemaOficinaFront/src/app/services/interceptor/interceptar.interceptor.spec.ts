import { TestBed } from '@angular/core/testing';

import { Interceptor } from './interceptar.interceptor';

describe('InterceptarInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      Interceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: Interceptor = TestBed.inject(Interceptor);
    expect(interceptor).toBeTruthy();
  });
});
