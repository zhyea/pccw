package org.chobit.cm.web;

import org.chobit.cm.ApiTestBase;
import org.junit.Assert;
import org.junit.Test;

public class MyApiTest extends ApiTestBase {

    @Override
    protected String parentPath() {
        return "/api/zhyea";
    }


    @Test
    public void get() {
        int i = testGet("/1", Integer.class);
        Assert.assertEquals(1, i);
    }


}
