package studentmanagement;

public class User {
    private String username;
    private String password;
    private String idCard;
    private String phone;

    public User() {
    }

    public User(String username, String password, String idCard, String phone) {
        this.username = username;
        this.password = password;
        this.idCard = idCard;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
