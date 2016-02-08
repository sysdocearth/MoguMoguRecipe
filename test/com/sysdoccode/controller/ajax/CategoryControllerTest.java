package com.sysdoccode.controller.ajax;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.sysdoccode.controller.recipe.CategoryController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CategoryControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/ajax.category");
        CategoryController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
