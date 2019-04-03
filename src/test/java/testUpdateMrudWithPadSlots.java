import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

public class testUpdateMrudWithPadSlots {

    @Before
    public void setUp() {
        RestAssured.delete("http://54.93.88.239/mruds/777");
        RestAssured.put("http://54.93.88.239/mruds/1");
    }

    @After
    public void tearDown() {
        RestAssured.delete("http://54.93.88.239/mruds/1");
    }

    @Test
    public void updateMrudWithPafSlot0()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                put("http://54.93.88.239/mruds/1/pafSlots/slot0/paf").
        then().
                assertThat().
                statusCode(200);

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("pafSlots.slot0", equalTo("paf"));

    }

    @Test
    public void updateMrudWithPafSlot1()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                put("http://54.93.88.239/mruds/1/pafSlots/slot1/paf").
        then().
                assertThat().
                statusCode(200);

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("pafSlots.slot1", equalTo("paf"));

    }

    @Test
    public void updateMrudWithPafSlot2()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                put("http://54.93.88.239/mruds/1/pafSlots/slot2/paf").
        then().
                assertThat().
                statusCode(200);

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("pafSlots.slot2", equalTo("paf"));

    }

    @Test
    public void updateMrudWithPafSlot3ShouldNotBeAllowed()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                put("http://54.93.88.239/mruds/1/pafSlots/slot3/paf").
        then().
                assertThat().
                statusCode(404);

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("pafSlots", not(hasKey("slot3")));

    }

    @Test
    public void updateNotExistingMrudWithPafSlot0IsNoAllowed()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                put("http://54.93.88.239/mruds/777/pafSlots/slot0/paf").
        then().
                assertThat().
                statusCode(404);

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/777").
        then().
                assertThat().
                statusCode(404);
    }
}
