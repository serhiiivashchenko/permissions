package permissions;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import steps.RolePrivileges;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class RolesAndPermissionBaseStory {
    @Parameterized.Parameter
    public CustomerRole role;

    RolePrivileges userSteps;

    public void verifyRolePermissions() {
        List<Pair<String, Boolean>> permissions = getMethodsFromXml();
        checkTestMethod(permissions);
    }
    public List<Pair<String, Boolean>> getMethodsFromXml() {
        return role.getPermissionsList();
    }
    public void checkTestMethod(List<Pair<String, Boolean>> permissions) {
        for (Pair entry : permissions) {
            Boolean methodResult = runMethod((String) entry.getKey());
            if (entry.getValue().toString().equals("true")) {
                Assert.assertTrue(entry.getKey() + " positive test method was not finished successfully for "
                        + role.name(), methodResult);
            } else {
                Assert.assertFalse(entry.getKey() + " negative test method was finished successfully for "
                        + role.name(), methodResult);}}
    }
    public Boolean runMethod(String methodName) {
        Boolean result = null;
        Class c = RolePrivileges.class;
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                try {
                    result = (Boolean) method.invoke(userSteps);  //TODO : implement methods that you run by name
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();}}
        }
        Assert.assertNotNull("Method has not been found or invoked", result);
        return result;
    }
}
