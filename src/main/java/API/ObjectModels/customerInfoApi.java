package API.ObjectModels;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class customerInfoApi {



    public final String VALID_GET_CUSTOMER_INFO_ENDPOINT = "whoami";
    public final String INVALID_GET_CUSTOMER_INFO_ENDPOINT = "whoami123";

    SHAFT.API apiObjectRequest;



    //constructor
    public customerInfoApi(SHAFT.API request) {
        this.apiObjectRequest = request;
    }


    //Happy Scenarios
    public Response postCustomerLogin(String token) {
        return apiObjectRequest.get(VALID_GET_CUSTOMER_INFO_ENDPOINT)
                .addHeader("Authorization","Bearer "+token)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }
    public Response postCustomerLoginWithInvalidEndpoint(String token) {
        return apiObjectRequest.get(INVALID_GET_CUSTOMER_INFO_ENDPOINT)
                .addHeader("Authorization","Bearer "+token)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }
    public Response postCustomerLoginWithoutToken() {
        return apiObjectRequest.get(VALID_GET_CUSTOMER_INFO_ENDPOINT)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
                .perform();
    }

}
