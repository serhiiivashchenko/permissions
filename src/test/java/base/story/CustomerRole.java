package base.story;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public enum CustomerRole {

    ADMINISTRATOR("resources/admin.xml"),
    WRITER("resources/writer.xml"),
    USER("resources/user.xml");

    private String initXml;

    CustomerRole(String initXml) {
        this.initXml= initXml;
    }
    public String getInitXml(){
        return initXml;
    }
    public List<Pair<String, Boolean>> getPermissionsList() {
        List<Pair<String, Boolean>> permissions = new ArrayList<>();
        // logic for retrieving a permission list from a file
        return permissions;
    }
}
