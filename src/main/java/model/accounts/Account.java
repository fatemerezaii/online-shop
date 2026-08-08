package model.accounts;

public class Account {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public Account(String email, String password, String phoneNumber, String username) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
