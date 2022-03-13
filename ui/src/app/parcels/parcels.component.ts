import { Component, OnInit, Input } from '@angular/core';
import {Router} from "@angular/router";
import { Parcel } from '../shared/parcel';
import {FormBuilder} from "@angular/forms";
import { MatTableDataSource } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-parcels',
  templateUrl: './parcels.component.html',
  styleUrls: ['./parcels.component.scss']
})
export class ParcelsComponent implements OnInit {
  dataSource: MatTableDataSource<Parcel>;
  displayedColumns = ['id', 'receipient', 'weight', 'value', 'signedByInsurance', 'handled', 'handle'];
  constructor(private fb: FormBuilder, 
    private router: Router,
    private http: HttpClient,
    private snackBar: MatSnackBar
    ) {}
  
  ngOnInit() {
    this.http.get<Parcel[]>('/api/containers/68465468')
    .subscribe(
      res => {
        this.dataSource = new MatTableDataSource(res);
    });
  }

  handle(parcel: Parcel) {
    this.http.post('/api/containers/68465468/parcel/'+ parcel.id, null).subscribe(
      x => {
        this.snackBar.open('Your parcel has been handled', 'Done', {
          duration: 1500,
        });
        setTimeout(() => this.router.navigateByUrl('/containers', { skipLocationChange: true }).then(() => {
          this.router.navigate([decodeURI('/containers')]);
        }), 1500);

      },
      e => {
        this.snackBar.open(e, 'An error has occured', {
          duration: 4000,
        });
      }
    );
  }
}
