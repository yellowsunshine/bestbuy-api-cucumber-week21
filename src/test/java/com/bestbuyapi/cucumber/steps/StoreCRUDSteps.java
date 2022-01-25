package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyinfo.StoresSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class StoreCRUDSteps {

    public static String name = "Scotland";
    public static int storeID;

    @Steps
    StoresSteps storesSteps;

    @When("^User creates new store record with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCreatesNewStoreRecordWith(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours){
        storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours).log().all().statusCode(201);
    }


    @And("User can search new record using store \"([^\"]*)\"$")
    public void userCanSearchNewRecordUsingStore(String name)  {
        HashMap<String,Object> storeRecord = storesSteps.getStoreInformationWithName(name);

        storeID = (int) storeRecord.get("id");
        Assert.assertThat(storeRecord,hasValue(name));
    }


    @And("^User can update new record using storeID, name, \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCanUpdateNewRecordUsingStoreIDName(String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours)  {
        name = name+ TestUtils.getRandomValue();
        storesSteps.changeStoreInfo(storeID,name,type,address,address2,city,state,zip,lat,lng,hours).log().all().statusCode(200);
    }

    @And("User can delete new record of storeID$")
    public void userCanDeleteNewRecordOfStoreID() {
        storesSteps.deleteStoreRecord(storeID).log().all().statusCode(200);

    }

    @Then("User is able to run successful CRUD operation on store details$")
    public void userIsAbleToRunSuccessfulCRUDOperationOnStoreDetails() {
        storesSteps.verifyStoreRecord(storeID).log().all().statusCode(404);
    }



}
