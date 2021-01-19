package googleBot;

import org.junit.Test;
import parentBot.ParentBot;
import static libs.Utils.*;


public class SearchStupidThings extends ParentBot {

    @Test
    public void searchRandomThings() throws InterruptedException {
        googlePage.openPage();
        googlePage.enterTextToSearchInput("some text");
        waitABit(5);
    }

}
