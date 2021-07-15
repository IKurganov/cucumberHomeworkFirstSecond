package RestAssured;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.Matchers.equalTo;

public class PetstoreUserTest {

    @Test
    public void checkCreateUser() {
        /*
        Проверить успешное создание юзера
        Вызвать гет метод для пользователя тестового - 200 статус и несколько параметров
        */
        Response response;
        PetstoreUser user;
        PetstoreUserApi userApi = new PetstoreUserApi();
        String username = "internetName1";

        user = PetstoreUser.builder()
                .email("Test@gmail.com")
                .firstName("name")
                .id(100L)
                .lastName("secondName")
                .password("password")
                .phone("8-800-555-35-35")
                .userStatus(1L)
                .username(username)
                .build();

        response = userApi.createUser(user);
        // успешное создание
        response.
                then().
                log().all().
                statusCode(200).
                body("message", equalTo("100"));
        System.out.println("Теперь поищем тестового юзера");
        System.out.println("Будем искать при помощи запроса GET");
        response = userApi.getUser(username);
        response.then().
                log().all().
                statusCode(200).
                body("firstName", equalTo("name")).
                body("lastName", equalTo("secondName")).
                body("phone", equalTo("8-800-555-35-35"));

    }

    @Test
    public void getUnknownUser() {
        /*
        Проверить получение ошибки not found неизвестного юзера
        Вызвать гет метод для пользователя - 404 статус и несколько параметров
        */
        String username = "unknownUser";
        Response response;
        PetstoreUserApi userApi = new PetstoreUserApi();

        response = userApi.getUser(username);
        response.then().
                log().all().
                statusCode(404).
                body("type", equalTo("error")).
                body("message", equalTo("User not found"));

    }

    @Test
    public void getTestUser() {
        /*
        Проверить успешное получение информации о юзере с сайта petstore - user1
        Вызвать гет метод для пользователя - 200 статус и несколько параметров
        */
        String username = "user1";
        Response response;
        PetstoreUserApi userApi = new PetstoreUserApi();

        response = userApi.getUser(username);
        response.then().
                log().all().
                statusCode(200).
                body("id", equalTo(100000000)).
                body("firstName", equalTo("Test"));

    }

    @Test
    public void checkIdFromCreatedUser() {
        /*
        Проверить успешное создание юзера
        Вызвать гет метод для пользователя тестового - 200 статус и несколько параметров
        */
        Response response;
        PetstoreUser user;
        PetstoreUserApi userApi = new PetstoreUserApi();
        String username = "internetName2";

        user = PetstoreUser.builder()
                .email("Test1@gmail.com")
                .firstName("name1")
                .id(228L)
                .lastName("secondName1")
                .password("password1")
                .phone("8-800-555-35-351")
                .userStatus(2L)
                .username(username)
                .build();

        response = userApi.createUser(user);
        // успешное создание
        response.
                then().
                log().all().
                statusCode(200);
        String actualCode = response.jsonPath().get("message");
        Assert.assertEquals("228", actualCode);
    }

}
