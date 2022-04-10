package api;

import org.apache.commons.lang3.RandomStringUtils;

public class LoginCourierDto {
    private String login;
    private String password;

    public LoginCourierDto() {
    }

    public LoginCourierDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static LoginCourierDto createRandom() {
        return new LoginCourierDto(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }
}
