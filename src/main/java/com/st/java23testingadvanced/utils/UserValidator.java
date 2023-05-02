package com.st.java23testingadvanced.utils;

import com.st.java23testingadvanced.exceptions.UserValidationException;
import com.st.java23testingadvanced.models.User;
import com.st.java23testingadvanced.models.UserType;

/**
 * Validation utils for user
 *
 * @author Vinod John
 * @Date 02.05.2023
 */
public class UserValidator {
    public boolean isAdmin(User user) throws UserValidationException {
        String fullName = user.getFirstName() + " " + user.getLastName();

        if (user.getUserType() == null) {
            throw new NullPointerException(String.format("The user type is null for user: %s", fullName));
        }

        if (UserType.ADMIN.equals(user.getUserType())) {
            return true;
        } else {
            throw new UserValidationException(fullName, "User admin check failed!");
        }
    }

    public String encodePassword(String password) {
        int midIndex = password.length() / 2;
        return password.substring(0, midIndex) + "#sda_java#" + password.substring(midIndex);
    }
}
