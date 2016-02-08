package com.sysdoccode.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SystemInfoTest extends AppEngineTestCase {

    private SystemInfo model = new SystemInfo();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
