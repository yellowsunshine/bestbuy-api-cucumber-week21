package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyinfo.ProductsSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductCRUDSteps {

    public static String name="Marvel comics";
    public static int productID;

    @Steps
    ProductsSteps productsSteps;


    @When("User creates new product record with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCreatesNewProductRecordWith(String name, String type, double price, String upc, int shipping, String description, String manufacturer, String model, String url, String image) {
        productsSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).log().all().statusCode(201);
    }

    @And("User can search new record using product \"([^\"]*)\"$")
    public void userCanSearchNewRecordUsingProduct(String name) {
        HashMap<String,Object> product = productsSteps.verifyProductInformation(name);

        productID = (int) product.get("id");

        Assert.assertThat(product,hasValue(name));
    }

    @And("User can update new record using productID, name, \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userCanUpdateNewRecordUsingProductIDName(String type, double price, String upc, int shipping, String description, String manufacturer, String model, String url, String image) {
        name= name+TestUtils.getRandomValue();

        productsSteps.changeProduct(productID,name, type, price, upc, shipping, description, manufacturer, model, url, image).log().all();
    }

    @And("User can delete new record of productID$")
    public void userCanDeleteNewRecordOfProductID() {
        productsSteps.deleteProduct(productID).log().all().statusCode(200);
    }

    @Then("User is able to run successful CRUD operation on product details$")
    public void userIsAbleToRunSuccessfulCRUDOperationOnProductDetails() {
        productsSteps.verifyProductDeleted(productID).log().all().statusCode(404);
    }
}
