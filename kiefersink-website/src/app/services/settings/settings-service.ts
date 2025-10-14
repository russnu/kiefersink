import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
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
  private apiUrl = 'http://localhost:8080/api/settings';

  constructor(private http: HttpClient) {}

  getSettings(settingKey: string): Observable<Settings> {
    return this.http.get<Settings>(`${this.apiUrl}/${settingKey}`);
  }
}
