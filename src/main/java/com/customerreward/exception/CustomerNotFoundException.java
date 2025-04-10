package com.customerreward.exception;

 /* Exception thrown when a customer is not found.*/

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {

        super(message);
    }
}


