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

        $x("//input[contains(@placeholder,\"Город\")]").setValue("Кострома");
        $x("//input[contains(@placeholder,\"Дата встречи\")]").sendKeys(Keys.CONTROL + "A");
        $x("//input[contains(@placeholder,\"Дата встречи\")]").sendKeys(Keys.DELETE);
        $x("//input[contains(@placeholder,\"Дата встречи\")]").setValue(planningDate);
        $x("//*[@name='name']").setValue("Андрей Андрей-Андрей");
        $$("[type='tel']").last().setValue("+79711253871");
        $(".checkbox").click();
        $$(".button").last().click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
