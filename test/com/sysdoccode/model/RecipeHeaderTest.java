package com.sysdoccode.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RecipeHeaderTest extends AppEngineTestCase {

    private RecipeHead model = new RecipeHead();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
