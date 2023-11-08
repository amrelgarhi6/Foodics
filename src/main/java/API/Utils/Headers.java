package API.Utils;

import java.util.HashMap;
import java.util.Map;

public class Headers {






    public static Map<String, String> headersForCustomerInfo(String generatedToken)
        {
            Map<String, String> headersForCustomerLogin = new HashMap<String, String>();
            headersForCustomerLogin.put("Authorization", generatedToken);
            return headersForCustomerLogin;
        }    }
