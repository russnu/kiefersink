import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Settings } from '../../models/settings';

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
