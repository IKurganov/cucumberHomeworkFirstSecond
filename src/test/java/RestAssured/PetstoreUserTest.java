package RestAssured;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

public class PetstoreUserTest {

    PetstoreApi petstoreApi = new PetstoreApi();

    @Test
    @DisplayName("Проверка создания юзера и метода получения информации о нем")
    public void checkCreateUser() throws InterruptedException {
        /*
        Проверить успешное создание юзера
        Вызвать гет метод для пользователя тестового - 200 статус и несколько параметров

         !!!Тест- частично флаки из-за метода ГЕТ, но это проблемы самого сервиса
        */


        String username = new Faker().name().firstName();
        String firstName = new Faker().name().firstName();

        PetstoreUser user = PetstoreUser.builder()
                .email("Test@gmail.com")
                .firstName(firstName)
                .id(100L)
                .lastName("secondName2")
                .password("password")
                .phone("8-800-555-35-35")
                .userStatus(1L)
                .username(username)
                .build();
        Response response = petstoreApi.createUser(user);

        // успешное создание
        response.
                then().
                log().all().
                statusCode(200).
                body("message", equalTo("100"));

        // надо подождать, пока наш сервис отработает - иначе падает ошибка
        Thread.sleep(4500);

        response = petstoreApi.getUser(username);
        response.then().
                log().all().
                statusCode(200).
                body("firstName", equalTo(firstName)).
                body("lastName", equalTo("secondName2")).
                body("phone", equalTo("8-800-555-35-35"));

    }

    @Test
    @DisplayName("Проверка реакции системы при запросе неизвестного пользователя")
    public void getUnknownUser() {
        /*
        Проверить получение ошибки not found неизвестного юзера
        Вызвать гет метод для пользователя - 404 статус и несколько параметров
        */
        String username = "unknownUser";
        Response response;

        response = petstoreApi.getUser(username);
        response.then().
                log().all().
                statusCode(404).
                body("type", equalTo("error")).
                body("message", equalTo("User not found"));

    }


    @Test
    @DisplayName("Выпиленный флаки тест")
    public void getTestUser() {

        // К сожалению, это флаки тест - но я добавил новый + гет проверяется в первом тесте

        /*

   //        Проверить успешное получение информации о юзере с сайта petstore - user1
   //   Вызвать гет метод для пользователя - 200 статус и несколько параметров


        //рандомизация не требуется - тестовый юзер на стороне сервиса
        String username = "user2";

        Response response = userApi.getUser(username);

        response.then().
                log().all().
                statusCode(200).
                body("id", equalTo(2)).
                body("firstName", equalTo("first2"));

        */
    }

    @Test
    @DisplayName("Просто проверяю отдельно ID у юзера, созданного ранее")
    public void checkIdFromCreatedUser() {
        /*
        Проверить успешное создание юзера
        Вызвать гет метод для пользователя тестового - 200 статус и несколько параметров
        */
        Response response;
        PetstoreUser user;
        String username = "internetName2";
        Long id = 228L;

        user = PetstoreUser.builder()
                .email("Test1@gmail.com")
                .firstName("name2")
                .id(id)
                .lastName("secondName2")
                .password("password1")
                .phone("8-800-555-35-351")
                .userStatus(2L)
                .username(username)
                .build();

        response = petstoreApi.createUser(user);
        // успешное создание
        response.
                then().
                log().all().
                statusCode(200);
        String actualId = response.jsonPath().get("message");
        Assert.assertEquals(String.valueOf(id), actualId);
    }

    @Test
    @DisplayName("Проверка создания животного с добавлением новых классов описания реквеста и нового метода в PetstoreApi")
    public void checkCreateTestPet() {
        /*
        Проверить успешное создание животного в магазине - статус 200 и параметры
        */
        String petName = "Борис животное";
        String status = "lull"; // усыпить
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add("google.com");
        photoUrls.add("ya.ru");

        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder().id(5L).name("TestTag").build());

        Pet pet = Pet.builder()
                .id(1228L)
                .category(Category.builder().id(1L).name("TestPet").build())
                .name(petName)
                .photoUrls(photoUrls)
                .tags(tags)
                .status(status)
                .build();

        Response response = petstoreApi.createPet(pet);

        response.then().
                log().all().
                statusCode(200).
                body("name", equalTo(petName)).
                body("status", equalTo(status));

    }
}
