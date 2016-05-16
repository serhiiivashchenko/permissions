package permissions;

import javafx.util.Pair;
import org.joox.Match;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import utils.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.joox.JOOX.$;

public enum CustomerRole {

    ADMINISTRATOR("permission/admin.xml"),
    WRITER("permission/writer.xml"),
    USER("permission/user.xml");

    private String initXml;

    CustomerRole(String initXml) {
        this.initXml= initXml;
    }
    public String getInitXml(){
        return initXml;
    }


    public List<Pair<String, Boolean>> getPermissionsList() {
        List<Pair<String, Boolean>> permissions = new ArrayList<>();
        Document dashboard_info = null;
        try {
            dashboard_info = $(Resource.getResource(initXml)).document();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        Match permission_elements = $(dashboard_info).xpath(".//permission");
        for (Element permission : permission_elements) {
            permissions.add(new Pair<>(permission.getTextContent(), permission.getAttribute("case").equals("positive")));
        }
        return permissions;
    }

}
