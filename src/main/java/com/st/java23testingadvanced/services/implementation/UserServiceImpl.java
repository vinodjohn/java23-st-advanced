package com.st.java23testingadvanced.services.implementation;

import com.st.java23testingadvanced.exceptions.ServiceUnavailableException;
import com.st.java23testingadvanced.models.User;
import com.st.java23testingadvanced.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of User Service
 *
 * @author Vinod John
 * @Date 03.05.2023
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User getUserFromRemoteServer() throws ServiceUnavailableException {
        try {
            ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("https://www.google.com/user", User.class);
            return userResponseEntity.getBody();
        } catch(HttpServerErrorException httpServerErrorException) {
            throw new ServiceUnavailableException("Server unavailable!");
        }
    }
}
