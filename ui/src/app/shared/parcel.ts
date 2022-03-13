import {Receipient} from './receipient';

export interface Parcel {
    id: string;
    receipient: Receipient;
    weight: number;
    value: number;
    handled: boolean;
    signedByInsurance;
  }
