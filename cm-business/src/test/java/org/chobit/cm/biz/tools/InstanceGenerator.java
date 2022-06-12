package org.chobit.cm.biz.tools;

import org.chobit.cm.common.entity.User;
import org.chobit.common.tools.ShortCode;
import org.chobit.common.utils.StrKit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author robin
 */
public final class InstanceGenerator {


    public static List<User> genUsers() {
        List<User> users = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
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
