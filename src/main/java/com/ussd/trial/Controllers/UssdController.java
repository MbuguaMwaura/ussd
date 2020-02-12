package com.ussd.trial.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UssdController {

    @PostMapping("/")
    public String ussd(@RequestParam("sessionId") String sessionId,@RequestParam("serviceCode") String serviceCode, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("text") String text){
        String response = "";
        if(text.isEmpty()){
            response = "CON What would you want to check \n";
            response += "1. My Account \n";
            response += "2. My phone number";

        } else if (text.equals("1")) {
            // Business logic for first level response
            response = "CON Choose account information you want to view \n";
            response += "1. Account number \n";
            response += "2. Account balance";
            response += "3. https://www.imbank.com";

        }else if (text.equals("2")) {
            // Business logic for first level response
            // This is a terminal request. Note how we start the response with END
            response = "END Your phone number is " + phoneNumber;

        } else if(text.equals("1*1") ) {
            // This is a second level response where the user selected 1 in the first instance
            String accountNumber  = "ACC1001";

            // This is a terminal request. Note how we start the response with END
            response = "END Your account number is "+ accountNumber;

        } else if (text.equals("1*2") ) {
            // This is a second level response where the user selected 1 in the first instance
            String balance  = "KES 10,000";

            // This is a terminal request. Note how we start the response with END
            response = "END Your balance is "+ balance;
        }
        return response;
    }
}
