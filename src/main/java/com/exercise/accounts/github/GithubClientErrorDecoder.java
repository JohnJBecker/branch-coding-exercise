package com.exercise.accounts.github;

import com.exercise.accounts.exception.NotFoundException;
import com.exercise.accounts.exception.ServiceUnavailableErrorException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class GithubClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        // Customize your error handling based on the response status
        return switch (response.status()) {
            case 404 -> new NotFoundException("github user not found");
            case 500, 503 -> new ServiceUnavailableErrorException(response.reason());
            default -> defaultErrorDecoder.decode(methodKey, response);
        };
    }
}
