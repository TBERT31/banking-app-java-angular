import {Component, OnInit} from '@angular/core';
import {LightInfoInput} from "../../components/light-info/light-info.component";
import {FirstService} from "../../services/first-service/first-service";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit{

  accountInfoList: Array<LightInfoInput> = [];

  constructor(
    private firstService: FirstService
  ) {}

  ngOnInit() {
    this.initializeAccountInfo();
    this.firstService.findAllTransactions()
      .subscribe(
        {
          next: (data) => {
            console.log('transactions list : ', data);
          }
        }
      );
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
