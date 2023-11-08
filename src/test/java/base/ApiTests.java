package base;

import API.ObjectModels.customerInfoApi;
import API.ObjectModels.userLoginApi;
import API.Utils.StatusCodes;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ApiTests {

    private SHAFT.API apiObject;
    private userLoginApi loginApi;
    private customerInfoApi customerApi;
    private String name = "Clotilde Stark";
    private String token;



    @BeforeMethod
    public void loadResponseBody()
    {
        apiObject = new SHAFT.API(System.getProperty("BASE_API_URL"));
        loginApi = new userLoginApi(apiObject);
        customerApi = new customerInfoApi(apiObject);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////POSITIVE SCENARIOS/////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(description = "test happy scenario to get the login and generate a token")
    public void testCustomerLoginSuccessfully() {
        Response postLogin = loginApi.postCustomerLogin();
        System.out.println(postLogin.asPrettyString());

        Validations.assertThat().number(postLogin.statusCode()).isEqualTo(StatusCodes.SUCCESS).perform();
        Validations.assertThat().object(postLogin.contentType()).isEqualTo("application/json").perform();
        Validations.assertThat().object(postLogin.jsonPath().get("token")).isNotNull().perform();
        String getToken = postLogin.jsonPath().get("token");
        token = getToken.substring(5);
        System.out.println("Token is >>>"  + token);
    }
    @Test(description = "test happy scenario to get the login and generate a token",dependsOnMethods = "testCustomerLoginSuccessfully")
    public void testSendGetCustomerInfoSuccessfully() {
        Response postLogin = customerApi.postCustomerLogin(token);
        System.out.println(postLogin.asPrettyString());

        Validations.assertThat().number(postLogin.statusCode()).isEqualTo(StatusCodes.SUCCESS).perform();
        Validations.assertThat().object(postLogin.contentType()).isEqualTo("application/json").perform();
        Validations.assertThat().object(postLogin.jsonPath().get("user.name")).isNotNull().perform();
        Validations.assertThat().object(postLogin.jsonPath().get("user.name")).isEqualTo(name).perform();
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////NEGATIVE SCENARIOS/////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test(description = "test happy scenario to get the login and generate a token")
    public void testSendCustomerLoginWithInvalidEndpoint() {
        Response postLogin = loginApi.postCustomerLoginWithInvalidEndpoint();
        System.out.println(postLogin.asPrettyString());

        Validations.assertThat().number(postLogin.statusCode()).isEqualTo(StatusCodes.NOT_FOUND).perform();
        Validations.assertThat().object(postLogin.contentType()).isEqualTo("text/html; charset=UTF-8").perform();
    }
    @Test(description = "test happy scenario to get the login and generate a token")
    public void testCustomerLoginWithoutBody() {
        Response postLogin = loginApi.postCustomerLoginWithoutBody();
        System.out.println(postLogin.asPrettyString());

        Validations.assertThat().number(postLogin.statusCode()).isEqualTo(StatusCodes.NOT_ALLOWED).perform();
        Validations.assertThat().object(postLogin.contentType()).isEqualTo("text/html; charset=UTF-8").perform();
    }





    @Test(description = "test happy scenario to get the login and generate a token")
    public void testSendGetCustomerInfoWithInvalidEndpoint() {
        Response postLogin = customerApi.postCustomerLoginWithInvalidEndpoint(token);
        System.out.println(postLogin.asPrettyString());

        Validations.assertThat().number(postLogin.statusCode()).isEqualTo(StatusCodes.SUCCESS).perform();
        Validations.assertThat().object(postLogin.contentType()).isEqualTo("text/html; charset=UTF-8").perform();
    }

    @Test(description = "test happy scenario to get the login and generate a token")
    public void testSendGetCustomerInfoWithoutToken() {
        Response postLogin = customerApi.postCustomerLoginWithoutToken();
        System.out.println(postLogin.asPrettyString());

        Validations.assertThat().number(postLogin.statusCode()).isEqualTo(StatusCodes.SUCCESS).perform();
        Validations.assertThat().object(postLogin.contentType()).isEqualTo("text/html; charset=UTF-8").perform();
    }


}
