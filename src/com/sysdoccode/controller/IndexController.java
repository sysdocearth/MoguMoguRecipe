package com.sysdoccode.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/**
 * URL トップページにアクセスしたときのクラスです。
 * 
 * @author Y.Takeuchi
 * @version　1.0.0
 */
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        //リダイレクトします
        return redirect("/recipe/");
    }
}
