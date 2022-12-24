package ru.stqa.addressbook.model;

import ru.stqa.addressbook.tests.ContactCheck;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String homePhone;
  private String homePhone2;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmail;
  private String address;
  private String allAddress;

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

  public String getHomePhone2() {
    return homePhone2;
  }

  public ContactData withHomePhone2(String homePhone2) {
    this.homePhone2 = homePhone2;
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
    return email;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmail() {
    return allEmail;
  }

  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getAllAddress() {
    return allAddress;
  }

  public ContactData withAllAddress(String allAddress) {
    this.allAddress = allAddress;
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

  public String mergedPhones()
  {
    return Arrays.asList(getHomePhone(), getMobilePhone(), getWorkPhone(), getHomePhone2())
            .stream().filter((s) -> !s.equals(""))
            .map( ContactCheck::cleaned)
            .collect( Collectors.joining("\n"));
  }

  public String mergeEmails()
  {
    return Arrays.asList(getEmail(), getEmail2(), getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
