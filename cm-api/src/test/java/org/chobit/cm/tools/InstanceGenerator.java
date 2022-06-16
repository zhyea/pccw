package org.chobit.cm.tools;

import org.chobit.cm.common.entity.User;
import org.chobit.common.tools.ShortCode;
import org.chobit.common.utils.StrKit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author robin
 */
public final class InstanceGenerator {


    public static List<User> genUsers(int cnt) {
        List<User> users = new LinkedList<>();
        for (int i = 0; i < cnt; i++) {
            User u = new User();
            u.setUsername(ShortCode.gen());
            u.setEmail("robin" + i + "@zhyea.com");
            u.setName("robin-" + StrKit.format("00", i));
            users.add(u);
        }
        return users;
    }


    private InstanceGenerator() {
    }

}
