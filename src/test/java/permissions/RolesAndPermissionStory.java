package permissions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RolesAndPermissionStory extends RolesAndPermissionBaseStory{

    @Parameterized.Parameters(name = "Role under test is: {0}")
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {CustomerRole.ADMINISTRATOR},
                {CustomerRole.WRITER},
                {CustomerRole.USER},
        });
    }

    @Test
    public void permissions() {
        verifyRolePermissions();
    }
}
