import {Component, OnInit} from '@angular/core';
import {ContactDto} from "../../services/models/contact-dto";
import {ContactService} from "../../services/services/contact.service";
import {HelperService} from "../../services/helper/helper.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-contact-list',
  templateUrl: './my-contact-list.component.html',
  styleUrls: ['./my-contact-list.component.scss']
})
export class MyContactListComponent implements OnInit{

  contacts: Array<ContactDto> = [];
  userIdToDelete:any = -1;

  constructor(
    private contactService: ContactService,
    private helperService: HelperService,
    private router: Router
  ) {

  }

  ngOnInit() {
    this.contactService.findAllByUserId1({
      'user-id': this.helperService.userId
    }).subscribe({
      next: (data) => {
        this.contacts = data;
      }
    })
  }

  private findAllContactByUser() {
    this.contactService.findAllByUserId1({
      'user-id': this.helperService.userId
    }).subscribe({
      next: (data) => {
        this.contacts = data;
      }
    });
  }

  update(id:number | undefined){
    this.router.navigate(['user', 'new-contact', id]);
  }

  delete(){
    if(this.userIdToDelete){
      this.contactService.delete2({
        'contact-id': this.userIdToDelete
      }).subscribe({
        next: () => {
          this.findAllContactByUser();
        }
      });
    }
  }
}
