package ru.stqa.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String Email;
  private String Email2;
  private String Email3;
  private String allEmail;

  //  private String nickname;
//  private String title;
@Override
public String toString() {
  return "ContactData{" +
          "id='" + id + '\'' +
          ", firstname='" + firstname + '\'' +
          ", lastname='" + lastname + '\'' +
          '}';
}
  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String home) {
    this.homePhone = home;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String work) {
    this.workPhone = work;
    return this;
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

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getEmail() {
    return Email;
  }

  public ContactData withEmail(String email) {
    Email = email;
    return this;
  }

  public String getEmail2() {
    return Email2;
  }

  public ContactData withEmail2(String email2) {
    Email2 = email2;
    return this;
  }

  public String getEmail3() {
    return Email3;
  }

  public ContactData withEmail3(String email3) {
    Email3 = email3;
    return this;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstname, that.firstname)) return false;
    if (!Objects.equals(lastname, that.lastname)) return false;
    return Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
