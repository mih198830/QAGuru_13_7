package amazTest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.Arrays.asList;

public class ParamsTestExample {
    @ParameterizedTest(name = "Search item {0}, {1} and {2}")
    @ValueSource(strings = {"Kindle Oasis", "Kindle Paperwhite", "Amazon Fire"})
    void amazonItemSearchTest(String searchItem) {
        Selenide.open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue(searchItem);
        $("#nav-search-submit-button").pressEnter();
        $$(".sg-col-inner").find((text(searchItem))).shouldBe(visible);
    }


    @CsvSource(value = {
            "Kindle Oasis, Now with adjustable warm light",
            "Kindle Paperwhite, Kindle Paperwhite Signature Edition Essentials Bundle",
            "Amazon Fire, Fire 7"
    }
    )
    @ParameterizedTest(name = "Search item {0}, {1} and {2}")
    void amazonItemSearchCSV(String searchItem, String expectedResult) {
        Selenide.open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue(searchItem);
        $("#nav-search-submit-button").pressEnter();
        $$(".sg-row").find(text(expectedResult)).shouldHave(visible);
    }


    @CsvFileSource(resources = "/csvExample.csv")
    @ParameterizedTest(name = "Search item {0}, {1} and {2}")
    void amazonItemSearchCSVFile(String searchItem, String expectedResult) {
        Selenide.open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue(searchItem);
        $("#nav-search-submit-button").pressEnter();
        $$(".sg-row").find(text(expectedResult)).shouldHave(visible);
    }


    static Stream<Arguments>amazonItemSearchAdditionalSearch(){
        return Stream.of(
                Arguments.of("Kindle Oasis", asList("adjustable")),
                Arguments.of("Kindle Paperwhite", asList("Signature")),
                Arguments.of("Amazon Fire", asList("2019")
        ));
    }

    @MethodSource(value = "amazonItemSearchAdditionalSearch")
    @ParameterizedTest(name = "Search item {0}, {1}, {2}")
    void amazonItemSearchAdditionalSearch(String searchItem, List<String> expectedResult) {
        Selenide.open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue(searchItem);
        $("#nav-search-submit-button").pressEnter();
        $$(".sg-col-inner").shouldHave(CollectionCondition.texts(expectedResult));
    }


    @EnumSource(EnumExample.class)
    @ParameterizedTest
    void enumTest(EnumExample enumExample) {
        Selenide.open("https://www.amazon.com/");
        $("#twotabsearchtextbox").setValue(enumExample.desc);
        $("#nav-search-submit-button").pressEnter();
        $$(".sg-col-inner").shouldHave(CollectionCondition.texts(enumExample.desc));
    }
}
