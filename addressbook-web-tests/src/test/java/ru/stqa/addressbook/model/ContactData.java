package ru.stqa.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
//  private final String company;
//  private final String address;
//  private final String mobile;
//  private final String email;

  public ContactData(String firstname, String middlename, String lastname, String nickname, String title) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
//    this.company = company;
//    this.address = address;
//    this.mobile = mobile;
//    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

//  public String getCompany() {
//    return company;
//  }
//
//  public String getAddress() {
//    return address;
//  }
//
//  public String getMobile() {
//    return mobile;
//  }
//
//  public String getEmail() {
//    return email;
//  }
}
