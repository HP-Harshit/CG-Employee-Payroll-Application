import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {
  provideHttpClient,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, FormsModule],
  providers: [
    provideHttpClient(withInterceptorsFromDi()), // Provide HttpClient correctly with interceptors
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
