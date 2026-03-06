package pl.piomin.services.playground.model;

import java.util.Objects;
public class PersonAddress {

    private Integer id;
    private String city;
    private String street;
    private String houseNo;
    private int flatNo;

    public PersonAddress(Integer id, String city, String street, String houseNo) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.city = Objects.requireNonNull(city, "city must not be null");
        this.street = Objects.requireNonNull(street, "street must not be null");
        this.houseNo = Objects.requireNonNull(houseNo, "houseNo must not be null");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = Objects.requireNonNull(city, "city must not be null");
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = Objects.requireNonNull(street, "street must not be null");
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = Objects.requireNonNull(houseNo, "houseNo must not be null");
    }

    public int getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(int flatNo) {
        this.flatNo = flatNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonAddress that = (PersonAddress) o;
        return flatNo == that.flatNo && Objects.equals(id, that.id) && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(houseNo, that.houseNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, houseNo, flatNo);
    }

    @Override
    public String toString() {
        return "PersonAddress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", flatNo=" + flatNo +
                '}';
    }

}
