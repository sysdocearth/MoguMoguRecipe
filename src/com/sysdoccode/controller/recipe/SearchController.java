package com.sysdoccode.controller.recipe;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * 検索バーでのタイトル一部文字列検索の結果を返すクラスです。
 * 
 * @author Y.Takeuchi
 *　@version 1.0.0
 */
public class SearchController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        
        String searchTitle = null; //検索文字列
        List<RecipeHead> hitRecipeList = null; //検索文字列を含むレシピ情報
        try{
            //半角スペースのトリム
            searchTitle = StringUtils.rtrim(StringUtils.ltrim(param("title")));
            //全角スペースのトリム
            searchTitle = StringUtils.rtrim(StringUtils.ltrim(searchTitle, StringUtils.ZENKAKUSPACE), StringUtils.ZENKAKUSPACE);
            
            //何も入力されていない場合は、トップページへ（スペースをトリムした結果も含む）
            if(StringUtils.isBlank(searchTitle)){
                return forward("/recipe/error/notfound-search.jsp");
            }
            
            RecipeService recipeService = new RecipeService();
            List<RecipeHead> recipeHeadAll = recipeService.getAll(SystemInfoService.getInstance().get().getKey());
            
            hitRecipeList = search(recipeHeadAll, searchTitle);
            
        }catch(RecipeException rex){
                //データストアアクセスによるエラー
                System.err.println(rex.toString());
                requestScope("errormessage" , rex.toString());
                return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            if(searchTitle==null){
                return forward("/recipe/error/notfound-search.jsp");
            }
        }
        
        requestScope("hitRecipeList"    , hitRecipeList);
        requestScope("searchTitle"      , searchTitle);

        return forward("search.jsp");
    }
    
    /**
     * 検索文字列を含むレシピ情報を返します。
     * 
     * @param allRecipeHead レシピの全情報
     * @param searchTitle　検索文字列
     * @return　検索文字列を含むレシピ情報
     */
    private List<RecipeHead> search(List<RecipeHead> allRecipeHead, String searchTitle){
        List<RecipeHead> retRecipeHeadList = new ArrayList<>();
        
        for(RecipeHead recipeHead : allRecipeHead){
            String recipeTitle = recipeHead.getTitle();
            
            if(StringUtils.contains(recipeTitle, searchTitle))
            {
                retRecipeHeadList.add(recipeHead);
            }            
        }
        
        return retRecipeHeadList;
    }
}
