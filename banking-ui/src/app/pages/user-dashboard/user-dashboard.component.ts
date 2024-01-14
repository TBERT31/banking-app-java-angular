import {Component, OnInit} from '@angular/core';
import {LightInfoInput} from "../../components/light-info/light-info.component";
import {TransactionsService} from "../../services/services/transactions.service";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit{

  accountInfoList: Array<LightInfoInput> = [];

  constructor(
    private transactionService: TransactionsService
  ) {}

  ngOnInit() {
    this.initializeAccountInfo();
    this.transactionService.findAll1()
      .subscribe({
        next: (data) => {
          console.log(data);
        }
      });
  }

  private initializeAccountInfo(){
    this.accountInfoList = [
      {
        title: 'Account balance',
        amount: 21560,
        infoStyle: 'bg-primary'
      },
      {
        title: 'Highest transfert',
        amount: 2560,
        infoStyle: 'bg-warning'
      },
      {
        title: 'Highest deposit',
        amount: 2890,
        infoStyle: 'bg-success'
      }
    ]
  }
}
