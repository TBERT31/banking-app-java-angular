import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = '';

    if(token !== undefined && token !== null){
      //assigner le token;
      const authReq = req.clone({
        headers: new HttpHeaders({
          Authorization: 'Bearer '+token
        })
      });
      return next.handle(authReq);
    }

    return next.handle(req);
  }
}
