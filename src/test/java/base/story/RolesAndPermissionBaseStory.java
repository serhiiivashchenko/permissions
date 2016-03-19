package base.story;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.runners.Parameterized;
import steps.UserSteps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class RolesAndPermissionBaseStory {
    @Parameterized.Parameter
    public CustomerRole role;

    UserSteps userSteps;

    public void verifyRolePermissions() {
        List<Pair<String, Boolean>> list = getMethodsFromXml();
        checkTestMethod(list);
    }

    public void checkTestMethod(List<Pair<String, Boolean>> list) {
        for (Pair entry : list) {
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
        Class c = UserSteps.class;
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                try {
                    result = (Boolean) method.invoke(userSteps);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();}}}
        return result;
    }

    public List<Pair<String, Boolean>> getMethodsFromXml() {
        return role.getPermissionsList();
    }
}
