import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit{

  @Input() isAdmin = true;
  role = 'user';

  constructor() {
  }

  ngOnInit(): void{
    if(this.isAdmin){
      this.role='admin';
    }else{
      this.role = 'user';
    }
  }
}
