package de.faceco.mashovapi.components;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import de.faceco.mashovapi.API;
import org.jetbrains.annotations.NotNull;

/**
 * A data type for a contact in a contact list.
 *
 * The natural order of Contacts is alphabetical: familyName -> privateName -> city
 *
 * @see API#getClassMembers()
 * @see API#getGroupMembers(Group)
 * @see API#getGroupMembers(int)
 */
public final class Contact implements Comparable<Contact> {
  private String studentGuid;
  private String familyName;
  private String privateName;
  private String classCode;
  private int classNum;
  private String city;
  private String address;
  private String city1;
  private String address1;
  private String city2;
  private String address2;
  private String phone;
  private String cellphone;
  
  Contact() {
  
  }
  
  public String getStudentGuid() {
    return studentGuid;
  }
  
  public String getFamilyName() {
    return familyName;
  }
  
  public String getPrivateName() {
    return privateName;
  }
  
  public String getClassCode() {
    return classCode;
  }
  
  public int getClassNum() {
    return classNum;
  }
  
  public String getCity() {
    return city;
  }
  
  public String getAddress() {
    return address;
  }
  
  public String getCity1() {
    return city1;
  }
  
  public String getAddress1() {
    return address1;
  }
  
  public String getCity2() {
    return city2;
  }
  
  public String getAddress2() {
    return address2;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public String getCellphone() {
    return cellphone;
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("studentGuid", studentGuid).add("familyName",
        familyName).add("privateName", privateName).add("classCode", classCode).add("classNum",
        classNum).add("city", city).add("address", address).add("city1", city1).add("address1",
        address1).add("city2", city2).add("address2", address2).add("phone", phone).add(
            "cellphone", cellphone).toString();
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact contact = (Contact) o;
    return classNum == contact.classNum && Objects.equal(studentGuid, contact.studentGuid) && Objects.equal(familyName, contact.familyName) && Objects.equal(privateName, contact.privateName) && Objects.equal(classCode, contact.classCode) && Objects.equal(city, contact.city) && Objects.equal(address, contact.address) && Objects.equal(city1, contact.city1) && Objects.equal(address1, contact.address1) && Objects.equal(city2, contact.city2) && Objects.equal(address2, contact.address2) && Objects.equal(phone, contact.phone) && Objects.equal(cellphone, contact.cellphone);
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(studentGuid, familyName, privateName, classCode, classNum, city,
        address, city1, address1, city2, address2, phone, cellphone);
  }
  
  @Override
  public int compareTo(@NotNull Contact c) {
    return ComparisonChain.start()
        .compare(familyName, c.familyName)
        .compare(privateName, c.privateName)
        .compare(city, c.city)
        .result();
  }
}
