package edu.byu.cs.tweeter.model.service.request;

public class SignupRequest {

    private String name;
    private String alias;
    private String password;

    private SignupRequest() {}

    public SignupRequest(String name, String alias, String password) {
        this.name = name;
        this.alias = alias;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
