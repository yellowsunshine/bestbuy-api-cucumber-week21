package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyinfo.ServicesSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ServicesCRUDSteps {

    public static String name = "PhotoShop";
    public static int serviceID;

    @Steps
    ServicesSteps servicesSteps;


    @When("^User creates new service record with \"([^\"]*)\"$")
    public void userCreatesNewServiceRecordWith(String name)  {
        servicesSteps.createService(name).log().all().statusCode(201);
    }

    @And("^User can search new record using service \"([^\"]*)\"$")
    public void userCanSearchNewRecordUsingService(String name)  {
        HashMap<String,Object> service = servicesSteps.verifyServiceRecord(name);
        Assert.assertThat(service,hasValue(name));
        serviceID = (int) service.get("id");

    }

    @And("^User can update new record using serviceID, name$")
    public void userCanUpdateNewRecordUsingServiceIDName() {
        name = name+TestUtils.getRandomValue();

        servicesSteps.changeServiceRecord(serviceID, name).log().all().statusCode(200);
        HashMap<String,Object> service = servicesSteps.verifyServiceRecord(name);
        Assert.assertThat(service,hasValue(name));
    }

    @And("^User can delete new record of serviceID$")
    public void userCanDeleteNewRecordOfServiceID() {
        servicesSteps.deleteService(serviceID).log().all().statusCode(200);
    }

    @Then("^User is able to run successful CRUD operation on service records$")
    public void userIsAbleToRunSuccessfulCRUDOperationOnServiceRecords() {
        servicesSteps.verifyServiceRecordByID(serviceID).log().all().statusCode(404);
    }
}
