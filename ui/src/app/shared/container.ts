import {Parcel} from './parcel';

export interface Container {
  id: string;
  shippingDate: string;
  parcels: Parcel[];
}
