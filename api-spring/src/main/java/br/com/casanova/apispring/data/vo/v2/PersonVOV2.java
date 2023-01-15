package br.com.casanova.apispring.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVOV2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String address;

    private Date birthday;

    public PersonVOV2() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVOV2 that = (PersonVOV2) o;
        return id == that.id && name.equals(that.name) && address.equals(that.address) && birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, birthday);
    }
}
