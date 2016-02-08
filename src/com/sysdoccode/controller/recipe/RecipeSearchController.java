package com.sysdoccode.controller.recipe;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.meta.RecipeSearchMeta;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.RecipeSearch;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;

/**
 * [Ajax]　Json形式のモデル構造を レシピ検索に返すクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class RecipeSearchController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        //全レシピの情報取得
        RecipeService recipeService = new RecipeService();
        List<RecipeHead> recipeHeadList = recipeService.getAll(SystemInfoService.getInstance().get().getKey());
        
        ////////////////////////////////////
        //Json構造にするため モデルクラス
        ////////////////////////////////////
        List<RecipeSearch> recipeSearchList = new ArrayList<>();
        
        //labelはキー候補リスト
        //valueはテキストボックスに表示する値
        for(RecipeHead recipeHead : recipeHeadList){
            RecipeSearch recipeSearch = new RecipeSearch();
            recipeSearch.setKey     (recipeHead.getKey());
            recipeSearch.setLabel   (recipeHead.getTitle());
            recipeSearch.setValue   (recipeHead.getTitle());
            recipeSearchList.add(recipeSearch);
        }
        
        // List 型の結果をオブジェクト型の配列に変換する
        Object[] recipeSearchArray = recipeSearchList.toArray();
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(RecipeSearchMeta.get().modelsToJson(recipeSearchArray));
        response.flushBuffer();
        return null;
    }
}
