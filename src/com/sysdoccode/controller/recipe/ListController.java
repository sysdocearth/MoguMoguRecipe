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
 * カテゴリ別のレシピリストを表示するクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 *
 */
public class ListController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        SystemInfo systeminfo = SystemInfoService.getInstance().get();
        RecipeService recipeService = new RecipeService();
        
        RecipeHead.CATEGORY category = null;
        List<RecipeHead> recipeListByCategory = null;
        try{
            category = RecipeHead.CATEGORY.valueOf(param("kind"));
            
            //カテゴリごとのレシピ情報を取得
            recipeListByCategory = recipeService.getAllByCategory(systeminfo.getKey(), category);
            
        }catch(RecipeException rex){
            //データストアアクセスによるエラー
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //kindの指定が不正
            return forward("/recipe/error/notfound-error.jsp");
        }
        
        ////////////////////////////////////////////////////
        //存在しないKeyが指定された場合はrecipeListByCategoryはNull
        if(recipeListByCategory==null){
            return forward("/recipe/error.jsp");
        }
                
        requestScope("categoryDesc"     , category.getStrValue());
        requestScope("imgFileName"      , category.getImgFileName());
        requestScope("categoryList"     , recipeListByCategory);
        
        return forward("list.jsp");
    }
}
