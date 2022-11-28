package runners.backendautomation;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RestAssuredBackendAutomationRunner {
    private static String BASE_URI = "https://api.punkapi.com/";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void validateJSONResponseAndSchema_NumberPerPageIs20() {
        validateResponse(20);
    }

    @Test
    public void validateJSONResponseAndSchema_NumberPerPageIs5() {
        validateResponse(5);
    }

    @Test
    public void validateJSONResponseAndSchema_NumberPerPageIs1() {
        validateResponse(1);
    }

    private void validateResponse(int numberOfBeersPerPage) {
        Response httpResponse = given()
                .contentType(ContentType.JSON)
                .param("page", "2")
                .param("per_page", String.valueOf(numberOfBeersPerPage))
                .when()
                .get("/v2/beers")
                .then()
                .extract().response();

        Assertions.assertEquals(200, httpResponse.statusCode());
        httpResponse.then().assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchemas/beersJsonSchema.json"));

        List<String> beerNames = httpResponse.jsonPath().getList("name");
        System.out.print("Beer Names from the Response with params per page " + numberOfBeersPerPage + ": ");
        System.out.println(String.join(", ", beerNames));

        Assertions.assertEquals(numberOfBeersPerPage, beerNames.size());
    }
}
