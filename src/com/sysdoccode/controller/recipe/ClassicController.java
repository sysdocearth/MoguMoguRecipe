package com.sysdoccode.controller.recipe;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;
import com.sysdoccode.util.RecipeException;

/**
 * 定番レシピのリストを表示するクラスです。
 * 
 * @author Y.Takeuchi
 *　@version 1.0.0
 */
public class ClassicController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        SystemInfo systeminfo = SystemInfoService.getInstance().get();
        RecipeService recipeService = new RecipeService();
        
        List<RecipeHead> classicRecipeList = null;
        try{
            //定番レシピを取得
            classicRecipeList = recipeService.getAllClassicRecipe(systeminfo.getKey());
            
        }catch(RecipeException rex){
            //データストアアクセスによるエラー
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");   
        }
                
        requestScope("classicRecipeList"     , classicRecipeList);

        return forward("classic.jsp");
    }
}
