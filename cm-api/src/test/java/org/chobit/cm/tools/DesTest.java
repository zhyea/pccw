package org.chobit.cm.tools;

import org.junit.jupiter.api.Test;

public class DesTest {


    @Test
    public void check() {
        String secretKey = "两只烤鸭往北走";
        String str = "1";

        String encrypted = DES.encrypt(str, secretKey);
        System.out.println(encrypted);

        String src = DES.decrypt(encrypted, secretKey);
        System.out.println(src);
    }


    @Test
    public void decrypt(){
        String secretKey = "两只烤鸭往北走";
        String str = "scjdfsajyhfdjasfkldsaufdasfkdsjaifudsai";
        String src = DES.decrypt(str, secretKey);
        System.out.println(src);
    }


}
