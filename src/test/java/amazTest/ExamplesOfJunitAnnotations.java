package amazTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExamplesOfJunitAnnotations {
    @Disabled("Better then comment")
    @Test
    void exampleOfDisabled(){
        System.out.println("Example of @Disabled");
    }

    @DisplayName("Name to be displayed")
    @Test
    void exampleOfDisplayed(){
        System.out.println("Example of @Displayed");
    }
}
