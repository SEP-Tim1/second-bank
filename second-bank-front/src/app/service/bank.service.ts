import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CardInfoDTO } from '../dtos/card-info-dto';
import { MerchantDTO } from '../dtos/merchant-dto';
import { RedirectUrlDTO } from '../dtos/redirect-url-dto';

@Injectable({
  providedIn: 'root'
})
export class BankService {
  registerUrl = environment.banka2 + '/account/register';
  payUrl = environment.banka2 + '/account/payment/';
  invoiceUrl = environment.banka2 + '/invoice/';

  constructor(private _http: HttpClient) { }

  register(cardInfo: CardInfoDTO): Observable<MerchantDTO> {
    return this._http.post<MerchantDTO>(this.registerUrl, cardInfo);
  }

  pay(invoiceId: number, dto: CardInfoDTO): Observable<RedirectUrlDTO> {
    return this._http.post<RedirectUrlDTO>(this.payUrl + invoiceId, dto);
  }

  getInvoice(id: number): Observable<any> {
    return this._http.get(this.invoiceUrl + id);
  }
}
