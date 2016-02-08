package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead.CATEGORY;

/**
 * レシピ登録画面を表示するクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class CreateController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        //カテゴリリスト（select-box）
        requestScope("categoryList"     , CATEGORY.values());
        
        return forward("create.jsp");
    }
}
