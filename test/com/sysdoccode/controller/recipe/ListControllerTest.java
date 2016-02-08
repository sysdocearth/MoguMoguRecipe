package com.sysdoccode.controller.recipe;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import com.sysdoccode.controller.recipe.ListController;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ListControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/recipe/category/index");
        ListController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/recipe/category/index.jsp"));
    }
}
