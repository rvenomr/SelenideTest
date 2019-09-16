package org.rvr;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class ChromeTest {
    @Test
    public void userCanSearch() {
        open("https://google.com/ncr");
        new GooglePage().searchFor("Открытие");

        SearchResultsPage results = new SearchResultsPage();
        String text =results.getResults().texts().get(0);

        //Getting text before parentheses
        String textWithNumbers = text.split("\\(")[0];

        //Extract numbers only
        Pattern p = Pattern.compile("\\d+");
        StringBuilder stringBuilder = new StringBuilder("");
        Matcher m = p.matcher(textWithNumbers);
        while (m.find()) {
            stringBuilder.append(m.group());
        }
        Assert.assertTrue(new Integer(stringBuilder.toString()) > 10000);
    }
}
