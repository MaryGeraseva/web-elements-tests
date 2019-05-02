package common.dataProviders;

import java.io.Serializable;

public class UserLogInData implements Serializable {
    public String testId;
    public String username;
    public String password;
    public String expectedError;
    public String description;

    public UserLogInData(String testId, String username, String password, String expectedError, String description) {
        this.testId = testId;
        this.username = username;
        this.password = password;
        this.expectedError = expectedError;
        this.description = description;
    }
}
