package com.sysdoccode.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RecipeBodyTest extends AppEngineTestCase {

    private RecipeBody model = new RecipeBody();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
