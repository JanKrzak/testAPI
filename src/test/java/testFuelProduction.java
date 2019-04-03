import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class testFuelProduction {

    @Before
    public void setUp() {
        RestAssured.put("http://54.93.88.239/mruds/1");
    }

    @After
    public void tearDown() {
        RestAssured.delete("http://54.93.88.239/mruds/1");
    }


    @Test
    public void fuelProductionForEmptyPafSlot()
    {

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("fuelProductionRate", equalTo(0)).
                body("online", equalTo(false));
    }

    @Test
    public void fuelProductionForOnePafSlot()
    {

       //GIVEN
       RestAssured.put("http://54.93.88.239/mruds/1/pafSlots/slot0/paf");

       given()
                .contentType(ContentType.JSON).
       when().
                get("http://54.93.88.239/mruds/1").
       then().
                assertThat().
                statusCode(200).
                body("fuelProductionRate", equalTo(1)).
                body("online", equalTo(true));
    }

    @Test
    public void fuelProductionForTwoPafSlots()
    {

        //GIVEN
        RestAssured.put("http://54.93.88.239/mruds/1/pafSlots/slot0/paf");
        RestAssured.put("http://54.93.88.239/mruds/1/pafSlots/slot1/paf");

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("fuelProductionRate", equalTo(5)).
                body("online", equalTo(true));
    }

    @Test
    public void fuelProductionForThreePafSlots()
    {

        //GIVEN
        RestAssured.put("http://54.93.88.239/mruds/1/pafSlots/slot0/paf");
        RestAssured.put("http://54.93.88.239/mruds/1/pafSlots/slot1/paf");
        RestAssured.put("http://54.93.88.239/mruds/1/pafSlots/slot2/paf");

        given()
                .contentType(ContentType.JSON).
        when().
                get("http://54.93.88.239/mruds/1").
        then().
                assertThat().
                statusCode(200).
                body("fuelProductionRate", equalTo(9)).
                body("online", equalTo(true));
    }
}
