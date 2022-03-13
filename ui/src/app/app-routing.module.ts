import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContainerComponent } from './container/container.component';
import { LoginComponent } from './login/login.component';
import { ParcelsComponent } from './parcels/parcels.component';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'containers', component: ContainerComponent },
  { path: 'parcels', component: ParcelsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
