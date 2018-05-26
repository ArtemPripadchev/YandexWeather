import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YandexWeather {
    @Test
    public void startWebDriver() {

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe"); //для Chrome.
        WebDriver driver = new ChromeDriver(); //старт для Хрома
        driver.navigate().to("https://yandex.ru/pogoda/lipetsk");

        Map<String, String> map = new HashMap<String, String>();

        List<WebElement> days = driver.findElements(By.cssSelector(".forecast-briefly__day"));

        for (int i = 0; i < days.size(); i++) {
            String dd = days.get(i).findElement(By.cssSelector(".forecast-briefly__date")).getText();
            String td = days.get(i).findElement(By.cssSelector(".forecast-briefly__temp_day"))
                    .findElement(By.cssSelector(".temp__value")).getText();
            String tn = days.get(i).findElement(By.cssSelector(".forecast-briefly__temp_night"))
                    .findElement(By.cssSelector(".temp__value")).getText();
            String we = days.get(i).findElement(By.cssSelector(".forecast-briefly__condition")).getText().toLowerCase();

            String temp_positive = "";
            String temp_night_positive = "";

            if (td.contains("+")) {
                temp_positive = ", температура положительная";
            } else if (td.equals("0")) {
                temp_positive = "";
            } else {
                temp_positive = ", температура отрицательная";
            }
            if (tn.contains("+")) {
                temp_night_positive = ", температура положительная";
            } else if (tn.equals("0")) {
                temp_night_positive = "";
            } else {
                temp_night_positive = ", температура отрицательная";
            }

            String s = dd + " 2018 года, температура днем составляет: " + td + temp_positive + ", ночью: "
                    + tn + temp_night_positive + ", " + we;
            System.out.println(s);

            Assert.assertTrue(td.contains("+")); //Проверим, все ли температуры были положительными, если да, то Тест пройден:

            map.put(dd, td); //импорт ключа и соответствующего ему значения в HasheMap

        }
        driver.close();
        driver.quit();
    }
}

