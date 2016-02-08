package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.model.RecipeBody;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * 登録したレシピを変更更新するクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class UpdateEntryController extends Controller {
    
    RecipeService recipeService = new RecipeService();

    @Override
    public Navigation run() throws Exception {
        
        RecipeHead recipeHead = null;
        RecipeBody recipeBody = null;
        
        /////////////////////////////////
        //　更新前のレシピ情報を取得
        /////////////////////////////////
        Key headKey = null;
        try{
            headKey = asKey("key");
                        
            recipeHead = recipeService.get(headKey);
            recipeBody = recipeHead.getRecipeBodyRef().getModel();
        
        }catch(RecipeException rex){
            //データストアアクセスによるエラー
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //Keyが不正な場合
            return forward("/recipe/error/notfound-error.jsp");
        }
                
        /////////////////////////////////
        // Key と version の退避
        Key  orgRecipeHeadKey        = recipeHead.getKey();
        Long orgRecipeHeadVersion    = recipeHead.getVersion();
        Key  orgRecipeBodyKey        = recipeHead.getRecipeBodyRef().getModel().getKey();
        Long orgRecipeBodyVersion    = recipeHead.getRecipeBodyRef().getModel().getVersion();
        
        BeanUtil.copy(request, recipeHead);
        BeanUtil.copy(request, recipeBody);
        
        /////////////////////////////////
        // Key と Version の再設定
        recipeHead.setKey       (orgRecipeHeadKey);
        recipeHead.setVersion   (orgRecipeHeadVersion);
        recipeBody.setKey       (orgRecipeBodyKey);
        recipeBody.setVersion   (orgRecipeBodyVersion);
        
        //ひとまずメモ登録（評価なし）
        String check_invalid = param("check_invalid");
        if(!StringUtils.isEmpty(check_invalid)){
            recipeHead.setNoRatings("y");
        }else{
            recipeHead.setNoRatings("");
        }
        
        //定番レシピの登録
        String check_classic = param("check_classic");
        if(!StringUtils.isEmpty(check_classic)){
            recipeHead.setClassicRecipe("y");
        }else{
            recipeHead.setClassicRecipe("");
        }
        
        /////////////////////////////////
        //　レシピ情報を更新する
        /////////////////////////////////
        try{
            recipeHead = recipeService.update(recipeHead, recipeBody);
            recipeBody = recipeHead.getRecipeBodyRef().getModel();     
            
        }catch(Exception e){
            e.printStackTrace();
        }

        
        return redirect("/recipe/list?kind=" + recipeHead.getCategory() + "&complete=2");
    }
}
