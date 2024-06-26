import {Artist} from "./Artist";


export interface User {
  id?: number;
  username: string;
  email: string;
  password: string;
  artist?: Artist;
  created_at?: Date;
  updated_at?: Date;
  enabled?: boolean;
  authorities?: any;
  accountNonLocked?: boolean;
  credentialsNonExpired?: boolean;
  accountNonExpired?: boolean;
}
