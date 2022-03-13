import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog, MatSnackBar} from '@angular/material';
import {Container} from '../shared/container';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.scss']
})
export class ContainerComponent implements OnInit {
  dataSource: MatTableDataSource<Container>;
  displayedColumns = ['id', 'shippingDate', 'actions'];

  constructor(
    private router: Router, 
    private snackBar: MatSnackBar, 
    public dialog: MatDialog,
    private http: HttpClient
    ) { }

  ngOnInit() {
    this.http.get<Container[]>('/api/containers')
    .subscribe(
      res => {
        this.dataSource = new MatTableDataSource(res);
      
    });
  }

  select(container: Container) {
    this.router.navigateByUrl('/parcels');
  }
}
