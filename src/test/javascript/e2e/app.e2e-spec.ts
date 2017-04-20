import { GoleadorPage } from './app.po';

describe('goleador App', () => {
  let page: GoleadorPage;

  beforeEach(() => {
    page = new GoleadorPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('gol works!');
  });
});
