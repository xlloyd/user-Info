package com.example.userinfo;

public class User {
    private String fullname;
    private String email;
    private String phone;
    private String gender;
    private String country;
    private String state;
    private String interest;
    private String birthday;
    private String birthTime;

    public User(String fullname, String email, String phone, String gender, String country, String state, String interest, String birthday, String birthTime) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.country = country;
        this.state = state;
        this.interest = interest;
        this.birthday = birthday;
        this.birthTime = birthTime;
    }

    // Getters and setters (if needed)
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getInterest() { return interest; }
    public void setInterest(String interest) { this.interest = interest; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public String getBirthTime() { return birthTime; }
    public void setBirthTime(String birthTime) { this.birthTime = birthTime; }
}
