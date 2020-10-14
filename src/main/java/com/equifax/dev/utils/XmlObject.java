package com.equifax.dev.utils;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "queries")
public class XmlObject {
    public List<XmlObjectQuery> query = new ArrayList<XmlObjectQuery>();

    @XmlTransient
    public List<XmlObjectQuery> getQuery() {
        return query;
    }

    public void setQuery(List<XmlObjectQuery> query) {
        this.query = query;
    }
}
