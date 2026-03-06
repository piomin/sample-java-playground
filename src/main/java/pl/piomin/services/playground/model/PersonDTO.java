package pl.piomin.services.playground.model;

import java.util.Objects;
public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String street;
    private String houseNo;
    private int flatNo;

    public PersonDTO() {
    }

    public PersonDTO(Integer id, String firstName, String lastName, int age, String city, String street, String houseNo, int flatNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.street = street;
        this.houseNo = houseNo;
        this.flatNo = flatNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public int getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(int flatNo) {
        this.flatNo = flatNo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String firstName;
        private String lastName;
        private int age;
        private String city;
        private String street;
        private String houseNo;
        private int flatNo;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder houseNo(String houseNo) {
            this.houseNo = houseNo;
            return this;
        }

        public Builder flatNo(int flatNo) {
            this.flatNo = flatNo;
            return this;
        }

        public PersonDTO build() {
            return new PersonDTO(id, firstName, lastName, age, city, street, houseNo, flatNo);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return age == personDTO.age && flatNo == personDTO.flatNo && Objects.equals(id, personDTO.id) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(city, personDTO.city) && Objects.equals(street, personDTO.street) && Objects.equals(houseNo, personDTO.houseNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, city, street, houseNo, flatNo);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", flatNo=" + flatNo +
                '}';
    }

}
