package edu.byu.cs.tweeter.model.service.request;

import java.util.Arrays;

public class SignupRequest {

    private String firstName;
    private String lastName;
    private String alias;
    private String password;
    private byte[] profile = null;
    private String imageURL = null;

    private SignupRequest() {}

    public SignupRequest(String first_name, String last_name, String alias, String password, byte[] profile_pic) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.alias = alias;
        this.password = password;
        this.profile = profile_pic;

    }
    public SignupRequest(String first_name, String last_name, String alias, String password, String profile_pic) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.alias = alias;
        this.password = password;
        this.imageURL = profile_pic;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isURL() {
        return profile == null;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public byte[] getProfile() {
        return profile;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alias='" + alias + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
