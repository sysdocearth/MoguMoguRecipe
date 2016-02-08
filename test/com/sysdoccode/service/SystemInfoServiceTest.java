package com.sysdoccode.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SystemInfoServiceTest extends AppEngineTestCase {

    private SystemInfoService service = SystemInfoService.getInstance();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
