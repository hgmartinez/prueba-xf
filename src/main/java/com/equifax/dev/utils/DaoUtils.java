package com.equifax.dev.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DaoUtils extends Constants{

    public static List<XmlObjectQuery> gstQueries = null;

    @Autowired
    ApplicationContext applicationContext;

    private List<XmlObjectQuery> loadQueries(String location) throws IOException {
        XmlObject object = null;
        Resource[] xmlQueryFiles = ResourcePatternUtils.getResourcePatternResolver(applicationContext)
                .getResources(location);
        String xmlString = "";
        List<XmlObjectQuery> gstQueries = new ArrayList<XmlObjectQuery>();
        for (Resource queryFile : xmlQueryFiles) {
            InputStream inputStream = null;
            inputStream = queryFile.getInputStream();
            xmlString = IOUtils.toString(inputStream, DEFAULT_WEB_ENCODING);
            object = unXml(xmlString);
            gstQueries.addAll(object.getQuery());
        }
        Collections.sort(gstQueries);
        return gstQueries;
    }

    public String getQByName(String queryName) {
        String query = "";
        if (gstQueries == null)
            try {
                gstQueries = loadQueries(DAO_QUERIES_LOCATION);
            } catch (IOException e) {
                e.printStackTrace();
            }
        XmlObjectQuery objectToFind = new XmlObjectQuery();
        objectToFind.setName(queryName);
        int index = Collections.binarySearch(gstQueries, objectToFind);
        if (index >= 0)
            query = gstQueries.get(index).getValue();
        return query;
    }

    private static XmlObject unXml(String xmlString) {
        XmlObject output = new XmlObject();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(XmlObject.class);

            XMLInputFactory xif = XMLInputFactory.newInstance();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(xmlString));

            Unmarshaller unmarshaller = context.createUnmarshaller();
            output = (XmlObject) unmarshaller.unmarshal(xsr);
        } catch (JAXBException e) {
            System.out.println("Error a XML: " + e.getMessage());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return output;
    }
}
