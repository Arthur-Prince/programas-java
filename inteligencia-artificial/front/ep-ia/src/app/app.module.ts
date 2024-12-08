import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import { ChartsModule } from 'ng2-charts';
import { HttpClientModule } from '@angular/common/http';
import {MatListModule, MatSelectionList} from '@angular/material/list';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';







@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    MatFormFieldModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    ChartsModule,
    HttpClientModule,
    MatListModule,
    MatSelectModule,
    MatFormFieldModule, // it's redundant here since MatInputModule already exports it
    MatListModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
