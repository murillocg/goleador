import { enableProdMode } from '@angular/core';
import { ProdConfig } from './blocks/config/prod.config';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app.module';

ProdConfig();

if (module['hot']) {
    module['hot'].accept();
}

platformBrowserDynamic().bootstrapModule(AppModule);
