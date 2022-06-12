package org.chobit.cm;

import org.chobit.cm.common.model.ResultWrapper;
import org.chobit.common.utils.JsonKit;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author robin
 */
public abstract class ApiTestBase extends TestBase {


    @Autowired
    private TestRestTemplate restTemplate;


    protected abstract String parentPath();


    protected Object testGet(String path) {
        path = buildPath(path);
        ResponseEntity<ResultWrapper> response = restTemplate.getForEntity(path, ResultWrapper.class);
        ResultWrapper w = response.getBody();
        return getResponse(w);
    }

    protected <T> T testGet(String path, Class<T> tClass) {
        Object r = testGet(path);
        String json = JsonKit.toJson(r);
        return JsonKit.fromJson(json, tClass);
    }


    private String buildPath(String path) {
        String p = parentPath();
        if (!p.endsWith("/") && !path.startsWith("/")) {
            return p + "/" + path;
        } else {
            return p + path;
        }
    }

    private Object getResponse(ResultWrapper wrapper) {
        System.out.println(JsonKit.toJson(wrapper));

        Assertions.assertNotNull(wrapper);
        Assertions.assertEquals(HttpStatus.OK.value(), wrapper.getCode());

        return null == wrapper.getContent() ? "" : wrapper.getContent();
    }


}
