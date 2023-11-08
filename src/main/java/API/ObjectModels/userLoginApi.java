package API.ObjectModels;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static API.Utils.Body.USR_LOGIN_BODY;

public class userLoginApi {


    public final String VALID_POST_LOGIN_ENDPOINT = "login";
    public final String INVALID_POST_LOGIN_ENDPOINT = "login123";

    SHAFT.API apiObjectRequest;



    //constructor
    public userLoginApi(SHAFT.API request) {
        this.apiObjectRequest = request;
    }


    //Happy Scenarios
    public Response postCustomerLogin() {
        return apiObjectRequest.post(VALID_POST_LOGIN_ENDPOINT)
                .setRequestBody(USR_LOGIN_BODY)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(200)
        .perform();
    }

    public Response postCustomerLoginWithInvalidEndpoint() {
        return apiObjectRequest.post(INVALID_POST_LOGIN_ENDPOINT)
                .setRequestBody(USR_LOGIN_BODY)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(405)
                .perform();
    }

    public Response postCustomerLoginWithoutBody() {
        return apiObjectRequest.post(VALID_POST_LOGIN_ENDPOINT)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(302)
                .perform();
    }
}
