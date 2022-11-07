package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;



public class CardDeliveryHWTest {

    public String searchDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSend() {
//        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        String planningDate = searchDate(300);

        $("[data-test-id=\"city\"] input.input__control").setValue("Петропавловск-Камчатский");
        $("[placeholder=\"Дата встречи\"]").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[data-test-id=\"name\"] input.input__control").setValue("Андрей Андрей-Андрей");
        $("[data-test-id=\"phone\"] input.input__control").setValue("+79301111111");
        $("[data-test-id=\"agreement\"] span.checkbox__box").click();
        $("[type=\"button\"].button").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
