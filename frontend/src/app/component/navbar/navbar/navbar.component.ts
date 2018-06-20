import {Component, OnInit} from '@angular/core';
import {NavbarLink} from "../model/NavbarLink";
import {AccountService} from "../../../service/account/account.service";
import {RoleType} from "../../../domain/RoleType";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;

  constructor(private accountService: AccountService) {
  }

  links: NavbarLink[] = [];

  ngOnInit() {
    this.links = [];
    this.links.push(new NavbarLink('Početna', '/home'));
  }

  getUsername() {
    return this.username = this.accountService.getCurrentAccount().username;
  }

  userLoggedIn() {
    return this.accountService.isLoggedIn();
  }

  userIsAdmin() {
    let roles = this.accountService.getCurrentAccount().roles;
    for (let role of roles) {
      if (role.toLowerCase() === RoleType.ADMIN.toLowerCase()) {
        return true;
      }
    }

    return false;
  }

  userIsDoctor() {
    let roles = this.accountService.getCurrentAccount().roles;
    for (let role of roles) {
      if (role.toLowerCase() === RoleType.DOCTOR.toLowerCase()) {
        return true;
      }
    }
    return false;
  }

  logout() {
    this.accountService.logout();
  }
}
