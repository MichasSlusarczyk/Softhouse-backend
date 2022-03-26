package pl.polsl.softhouse.dto.user;

public class UserAuthDto extends BaseUserDto {

    protected String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
