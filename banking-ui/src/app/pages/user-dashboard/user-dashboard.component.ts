import {Component, OnInit} from '@angular/core';
import {LightInfoInput} from "../../components/light-info/light-info.component";
import {StatisticsService} from "../../services/services/statistics.service";
import {HelperService} from "../../services/helper/helper.service";
import {lastValueFrom} from "rxjs";
import {ChartType, ChartDataSets} from "chart.js";
import {Label} from "ng2-charts";
import {DatepickerOptions} from "ng2-datepicker";
import {DatePipe} from "@angular/common";


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
  chartType: ChartType = 'line';
  dataset: ChartDataSets[] = [];
  labels : Label[] = ['Sum Transactions by day'];
  chartOptions: any = {
    legend: {
      position: 'bottom',
      labels: {
        fontSize: 16,
        usePointStyle: true
      }
    }
  };

  dateOptions: DatepickerOptions = {
    format: 'yyyy-MM-dd'
  };
  startDate: Date = new Date();
  endDate: Date = new Date();

  constructor(
    private statisticsService: StatisticsService,
    private helperService: HelperService,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.initializeAccountInfo();
  }

  filterStatistics(){
    this.statisticsService.findSumTransactionsByDate({
      'user-id': this.helperService.userId,
      'start-date': this.datePipe.transform(this.startDate, 'yyyy-MM-dd') as string,
      'end-date':  this.datePipe.transform(this.endDate, 'yyyy-MM-dd') as string
    }).subscribe({
      next: (values) => {
        this.dataset = [];
        this.labels = [];
        const chartDataSet: ChartDataSets = {};
        const dataValues: Array<number> = [];
        for(let record of values){
          this.labels.push(record.transactionDate as string);
          dataValues.push(record.amount as number);
        }
        chartDataSet.data = dataValues;
        chartDataSet.label = 'Sum Transactions by day';
        this.dataset.push(chartDataSet);
      }
    });
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
