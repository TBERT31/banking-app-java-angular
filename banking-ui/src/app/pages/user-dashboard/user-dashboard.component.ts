import {Component, OnInit} from '@angular/core';
import {LightInfoInput} from "../../components/light-info/light-info.component";
import {StatisticsService} from "../../services/services/statistics.service";
import {HelperService} from "../../services/helper/helper.service";
import {lastValueFrom} from "rxjs";


@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit{

  accountInfoList: Array<LightInfoInput> = [];
  private accountBalance = 0;
  private highestDeposit = 0;
  private highestTransfert = 0;

  constructor(
    private statisticsService: StatisticsService,
    private helperService: HelperService
  ) {}

  ngOnInit() {
    this.initializeAccountInfo();
  }

  private async initializeAccountInfo(){

    this.accountBalance = await lastValueFrom(
      this.statisticsService.getAccountBalance({"user-id": this.helperService.userId})
    );

    this.highestDeposit = await lastValueFrom(
      this.statisticsService.highestDeposit({"user-id": this.helperService.userId})
    );

    this.highestTransfert = await lastValueFrom(
      this.statisticsService.highestTransfert({"user-id": this.helperService.userId})
    );

    this.accountInfoList = [
      {
        title: 'Account balance',
        amount: this.accountBalance,
        infoStyle: 'bg-primary'
      },
      {
        title: 'Highest transfert',
        amount: this.highestTransfert,
        infoStyle: 'bg-warning'
      },
      {
        title: 'Highest deposit',
        amount: this.highestDeposit,
        infoStyle: 'bg-success'
      }
    ]
  }
}
