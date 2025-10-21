import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
//==========================================================//
export interface Settings {
  settingKey: string;
  settingValue: string;
}
//==========================================================//
@Injectable({
  providedIn: 'root',
})
export class SettingsService {
  private apiUrl = `${environment.apiBaseUrl}/settings`;

  constructor(private http: HttpClient) {}

  getSettings(settingKey: string): Observable<Settings> {
    return this.http.get<Settings>(`${this.apiUrl}/${settingKey}`);
  }
}
