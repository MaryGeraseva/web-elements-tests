package pages.MultipluWindowsPage;

import pages.BasePage.BasePageObject;

public class SecondWindowPage extends BasePageObject {

    private String url = "http://the-internet.herokuapp.com/windows/new";

    private String SecondWindowExpectedText = "New Window";

    public String getUrl() {
        return url;
    }

    public String getSecondWindowExpectedText() {
        return SecondWindowExpectedText;
    }

}
