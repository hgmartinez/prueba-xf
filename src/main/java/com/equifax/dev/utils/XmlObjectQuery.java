package com.equifax.dev.utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

public class XmlObjectQuery implements Comparable<XmlObjectQuery> {
    @XmlValue
    private String name;
    @XmlValue
    private String value;

    @XmlTransient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int compareTo(XmlObjectQuery o) {
        return name.compareTo(o.getName());
    }
}
