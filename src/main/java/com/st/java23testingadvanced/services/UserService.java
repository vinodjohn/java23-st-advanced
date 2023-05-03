package com.st.java23testingadvanced.services;

import com.st.java23testingadvanced.exceptions.ServiceUnavailableException;
import com.st.java23testingadvanced.models.User;

/**
 * Service to handle user related operations
 *
 * @author Vinod John
 * @Date 03.05.2023
 */
public interface UserService {
    /**
     * To fetch a user from a remote server
     *
     * @return User
     */
    User getUserFromRemoteServer() throws ServiceUnavailableException;
}
