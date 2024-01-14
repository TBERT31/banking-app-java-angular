import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { filter, map } from 'rxjs/operators';
import { TransactionDto } from './transaction-dto';
import { Observable } from 'rxjs';

@Injectable()
export class FirstService {

  rootUrl = 'http://localhost:8099';

  constructor(
    private httpClient: HttpClient
  ) { }

  findAllTransactions(): Observable<any> {
    let _headers: HttpHeaders = new HttpHeaders();
    _headers = _headers.set('Authorization', 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aG9tYXMuYmVydGVhdTNAbWFpbC5jb20iLCJmdWxsTmFtZSI6InRob21hczMgYmVydGVhdTMiLCJleHAiOjE3MDU3NzU2NDEsInVzZXJJZCI6MTgsImlhdCI6MTcwNTA1NTY0MSwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XX0.zg0EFVcttYyEdV1LHTQBL7XB1qtJBgMNQP5tDyRk73g');
    const request = new HttpRequest<any>(
      'GET',
      this.rootUrl + '/transactions/',
      {
        headers: _headers,
        params: null,
        responseType: 'json'
      }
    );
    return this.httpClient.request(request)
      .pipe(
        filter(r => r instanceof HttpResponse),
        map(res => res as any)
      );
  }

  findAllTransactionsV2(): Observable<Array<TransactionDto>> {
    let _headers: HttpHeaders = new HttpHeaders();
    _headers = _headers.set('Authorization', 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aG9tYXMuYmVydGVhdTNAbWFpbC5jb20iLCJmdWxsTmFtZSI6InRob21hczMgYmVydGVhdTMiLCJleHAiOjE3MDU3NzU2NDEsInVzZXJJZCI6MTgsImlhdCI6MTcwNTA1NTY0MSwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XX0.zg0EFVcttYyEdV1LHTQBL7XB1qtJBgMNQP5tDyRk73g');
    return this.httpClient.get(
      this.rootUrl + '/transactions/',
      {
        headers: _headers
      }
    ).pipe(
      filter(r => r instanceof HttpResponse),
      map(res => {
        console.log(res);
        return (res as HttpResponse<TransactionDto>).body as Array<TransactionDto>;
      })
    );
  }
}
