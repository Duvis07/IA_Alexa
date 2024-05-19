/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package co.edu.iudigital.alexarobot;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

/**
 *
 * @author JULIO MARTINEZ
 */
public class AlexaRobot {

    private static final String PALABRA_MAGICA = "alexa";
    private static final String TUNEIN = "https://tunein.com/search/?query=";

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver","/ruta/chromedriver.exe");
        Scanner teclado = new Scanner(System.in);

        System.out.println("Di 'Alexa'...: ");

        String palabra = teclado.nextLine();

        do {
            if (palabra.equalsIgnoreCase(PALABRA_MAGICA)) {
                break;
            }
            System.out.println("Di 'Alexa'...: ");
            palabra = teclado.nextLine();
        } while (!palabra.equalsIgnoreCase(PALABRA_MAGICA));

        System.out.println("¡Hola! ¿Que emisora quieres escuchar?");

        palabra = teclado.nextLine();

        do {
            if (palabra != null && !palabra.isBlank() && !palabra.isEmpty()) {
                break;
            }
            System.out.println("Lo siento no te he entendido, repite por favor: ");
            palabra = teclado.nextLine();
        } while (palabra == null || palabra.isBlank() || palabra.isEmpty());

        System.out.println("Te pongo " + palabra + " en Tunein...");
        
         Thread.sleep(2000);
         
         
        WebDriver driver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));
        driver.get(TUNEIN + palabra);

       
        
        
        // Fill out credentials
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler")));
        WebElement buttonAccept = driver.findElement(By.id("onetrust-accept-btn-handler"));
        buttonAccept.click();
        
        Thread.sleep(5000);
        
        WebElement link = driver.findElements(By.xpath("//a[@data-testid='guideItemTitleLink']")).get(0);

        if(link != null) {
           if (link.getTagName() == null) {
            link = driver.findElements(By.xpath("//a[@data-testid='guideItemLink']")).get(0);
            if (link.getTagName() == null) {
                System.out.println("Lo siento, no pude encontrarla");
            } else {
                link.click();
            }
        }else{
            link.click();
        } 
        }else{
           System.out.println("Lo siento, no pude encontrarla"); 
        }


        /*if (driver != null) {
            driver.quit();
	}*/
    }
}
