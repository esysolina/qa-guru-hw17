package guru.qa;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    void getListUsersTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void createUserTest() {
        String data = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void updateUserTest() {
        String dataUpdate = "{ \"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .contentType(JSON)
                .body(dataUpdate)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    void deleteUserTest() {

        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void failedRegTest() {
        String dataReg = "{\"email\": \"sydney@fife\"}";

        given()
                .contentType(JSON)
                .body(dataReg)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
