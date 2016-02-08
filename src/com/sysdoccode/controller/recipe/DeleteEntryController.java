package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ApplicationMessage;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * 登録されれているレシピを削除するクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class DeleteEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {

        RecipeHead recipeHead = null;
        RecipeService recipeService = new RecipeService();
        
        try{
            Key recipeKey = asKey("key");
            recipeHead = recipeService.get(recipeKey);
            String password4delete =  StringUtils.rtrim(StringUtils.ltrim(asString("password")));
            
            ///////////////////////////////////////
            //入力された編集用パスワードが正しいかを確認
            if(!(recipeHead.getPassword()).equals(password4delete)){
                
                errors.put("message", ApplicationMessage.get("error.password"));
                return forward("/recipe/detail?key=" + recipeHead.getKey());                
            }
            
            ///////////////////////////////////////
            //対象のレシピを削除する
            recipeService.delete(asKey("key"));
            
        }catch(RecipeException rex){
            //データストアアクセスによるエラー
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //kindの指定が不正
            return forward("/recipe/error/notfound-error.jsp");
        }
        
        return redirect("/recipe/list?kind=" + recipeHead.getCategory() + "&complete=3" );
    }
}
