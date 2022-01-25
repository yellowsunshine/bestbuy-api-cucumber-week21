package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyinfo.CategoriesSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class CategoriesCRUDSteps {

    public static String name = "Learning Toys";



    @Steps
    CategoriesSteps categoriesSteps;


    @When("^User creates new category record with \"([^\"]*)\",\"([^\"]*)\"$")
    public void userCreatesNewCategoryRecordWith(String id, String name)  {
        categoriesSteps.createCategory(id,name).log().all().statusCode(201);
    }

    @And("^User can search new record using categoryID \"([^\"]*)\"$")
    public void userCanSearchNewRecordUsingCategoryID(String id)  {
        HashMap<String,Object> category = categoriesSteps.verifyCategoryRecord(id);
        Assert.assertThat(category,hasValue(id));
    }

    @And("^User can update new record using categoryID \"([^\"]*)\", name$")
    public void userCanUpdateNewRecordUsingCategoryIDName(String id)  {
        name = name+TestUtils.getRandomValue();
        categoriesSteps.changeCategoryRecord(id,name).log().all().statusCode(200);
    }

    @And("^User can delete new record of categoryID \"([^\"]*)\"$")
    public void userCanDeleteNewRecordOfCategoryID(String id) {
        categoriesSteps.deleteCategory(id).log().all().statusCode(200);
    }


    @Then("^User is able to run successful CRUD operation on category record with categoryID \"([^\"]*)\"$")
    public void userIsAbleToRunSuccessfulCRUDOperationOnCategoryRecordWithCategoryID(String id)  {
        categoriesSteps.verifyCategoryDeleted(id).log().all().statusCode(404);
    }
}
