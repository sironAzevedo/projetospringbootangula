import { AppSitePage } from './app.po';

describe('app-site App', function() {
  let page: AppSitePage;

  beforeEach(() => {
    page = new AppSitePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
