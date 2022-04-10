package api;

import org.apache.commons.lang3.RandomStringUtils;

public class RegisterCourierDto {
    private String login;
    private String password;
    private String firstName;

    public RegisterCourierDto() {
    }

    public RegisterCourierDto(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static RegisterCourierDto createRandom() {
        return new RegisterCourierDto(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }
}
