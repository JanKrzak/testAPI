import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class testCreateAndUpdateMrud {

    @Before
    public void setUp() {
        RestAssured.delete("http://54.93.88.239/mruds/888");
    }

    @After
    public void tearDown() {
        RestAssured.delete("http://54.93.88.239/mruds/1");

    }

    @Test
    public void createAndUpdateNewMrud()
    {

        given().
                contentType(ContentType.JSON).
        when().
                put("http://54.93.88.239/mruds/1").
        then().
                statusCode(200);

    }

    @Test
    public void createNewMrudUsingPostIsNotAllowed()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                post("http://54.93.88.239/mruds/1").
        then().
                statusCode(405);

    }

    @Test
    public void mrudIsExisting()
    {

        //BEFORE
        RestAssured.put("http://54.93.88.239/mruds/1");

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                statusCode(200);
    }

    @Test
    public void mrudIsNotExisting()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/888").
        then().
                statusCode(404);
    }

}
