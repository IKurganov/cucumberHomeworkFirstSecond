package RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetstoreApi {
    private RequestSpecification spec;

    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    private static final String USER = "/user";
    private static final String PET = "/pet";


    public PetstoreApi(){
        spec = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }

    public Response getUser(String username){
        return given(spec)
                .with()
                .log().all()
                .when()
                .get(USER +"/" + username);
    }

    public Response createUser(PetstoreUser user){
        return given(spec)
                .with()
                .body(user)
                .log().all()
                .when()
                .post(USER);
    }

    public Response createPet(Pet pet){
        return given(spec)
                .with()
                .body(pet)
                .log().all()
                .when()
                .post(PET);
    }


}
