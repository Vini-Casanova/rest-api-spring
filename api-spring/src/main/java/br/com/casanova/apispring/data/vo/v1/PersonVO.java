package br.com.casanova.apispring.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String address;

    public PersonVO() {}

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO personVO = (PersonVO) o;
        return id == personVO.id && name.equals(personVO.name) && address.equals(personVO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
