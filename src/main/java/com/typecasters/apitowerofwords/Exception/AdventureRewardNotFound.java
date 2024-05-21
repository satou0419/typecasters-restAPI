package com.typecasters.apitowerofwords.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdventureRewardNotFound extends RuntimeException {
    public AdventureRewardNotFound(String message) {
        super(message);
    }
}