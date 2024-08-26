package org.atg.assignment.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atg.assignment.dto.PetDTO;
import org.atg.assignment.util.PropertyManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains tests verification of PetStore API's
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetAPITest {

    private static final Logger LOG = LogManager.getLogger(PetAPITest.class);
    private static String petStoreApplicationURL;
    private static String petAPIPath;
    private static String findPetsByStatusAPIPath;
    private static final String STATUS_AVAILABLE = "available";
    private static Long petId;
    private static String petName;

    @BeforeAll
    public static void init() throws IOException {
        PropertyManager pm = PropertyManager.getInstance();
        petStoreApplicationURL = pm.getPetStoreApplicationURL();
        petAPIPath = pm.getPetAPIPath();
        findPetsByStatusAPIPath = pm.getFindPetsByStatusAPIPath();
    }

    @DisplayName("Test add a new pet to store")
    @Test
    @Order(1)
    public void addNewPetTest() {
        LOG.info("Going to call POST create pet api");
        PetDTO newPet = new PetDTO("atg doggie", "available");
        Response response = given().
                contentType(ContentType.JSON).
                body(newPet).
                when().
                post(petStoreApplicationURL + petAPIPath);
        response.
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "name", is(newPet.getName()),
                        "status", is(newPet.getStatus()),
                        "id", notNullValue()
                );
        petId = response.jsonPath().get("id");
        LOG.info("Pet created having petId: {}", petId);
    }

    @DisplayName("Test to search for new pet created")
    @Test
    @Order(2)
    void findPetsByStatusTest() {
        LOG.info("Going to call findPetsByStatus API with status: {}", STATUS_AVAILABLE);
        Response response = get(petStoreApplicationURL + petAPIPath + findPetsByStatusAPIPath + STATUS_AVAILABLE);
        response.then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK);
        List<Long> petIds = response.jsonPath().get("id");
        assertFalse(petIds.isEmpty(), "PetId from findPetsByStatus API should not be empty");
        LOG.info("Going to find petId: {}, in response for findPetsByStatus", petId);
        assertTrue(petIds.contains(petId), "PetIds from findPetsByStatus API should contain petId of new pet");
    }

    @DisplayName("Test to update the pet")
    @Test
    @Order(3)
    void updatePetTest() {
        petName = "atg updated doggie";
        PetDTO newPet = new PetDTO(petId, petName, "available");
        LOG.info("Going to update pet with petId: {}", petId);
        given().
                contentType(ContentType.JSON).
                body(newPet).
                when().
                put(petStoreApplicationURL + petAPIPath).
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "name", is(petName),
                        "status", is(newPet.getStatus()),
                        "id", is(petId),
                        "id", notNullValue()
                );
        LOG.info("Pet with petId: {} updated", petId);
    }

    @DisplayName("Test to find the pet by Id")
    @Test
    @Order(4)
    void findPetByIdTest() {
        LOG.info("Going to fetch pet with petId: {}", petId);
        get(petStoreApplicationURL + petAPIPath + "/" + petId)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "name", is(petName),
                        "status", is(STATUS_AVAILABLE),
                        "id", is(petId),
                        "id", notNullValue()
                );
        LOG.info("Pet with petId: {} fetched successfully", petId);
    }

    @DisplayName("Test to delete the pet by Id")
    @Test
    @Order(5)
    void deletePetTest() {
        LOG.info("Going to delete pet with petId: {}", petId);
        System.out.println(petId);
        delete(petStoreApplicationURL + petAPIPath + "/" + petId)
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.SC_OK)
                .body(
                        "message", is(petId.toString())
                );
        LOG.info("Pet with petId: {} deleted successfully", petId);
    }
}
