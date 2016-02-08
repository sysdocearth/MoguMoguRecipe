package com.sysdoccode.controller.recipe;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;

/**
 * ログイン後の初期画面を表示するクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
/*        
        //////////////////////////////////////
        //時間を計る　start
        long start1 = System.currentTimeMillis();
        /////////////////////////////////////////////
*/        
        //初期設定情報が登録されているかを確認
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        //登録されていない場合は、設定画面へ
        if(systemInfo == null){
            return redirect("/setup/");
        }
        
        RecipeService recipeService = new RecipeService();
        //全レシピ
        List<RecipeHead> recipeAllList = recipeService.getAll(systemInfo.getKey());
        
        //お肉のおかず
        List<RecipeHead> meatList       = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.MEAT);
        //お魚のおかず
        List<RecipeHead> fishList       = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.FISH);
        //お豆腐のおかず
        List<RecipeHead> eggTofuList    = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.EGGSTOFU);
        //主食・汁もの
        List<RecipeHead> mainSoupList   = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.MAINFSOUP);
        //副食
        List<RecipeHead> sideDishList   = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.SIDEDISH);
        //デザート
        List<RecipeHead> dessertList    = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.DESSERT);
        
        //全レシピ件数
        requestScope("allCount"        , recipeAllList.size());
        
        //カテゴリ別件数
        requestScope("meatCount"        , meatList.size());
        requestScope("fishCount"        , fishList.size());
        requestScope("eggTofuCount"     , eggTofuList.size());
        requestScope("mainSoupCount"    , mainSoupList.size());
        requestScope("sideDishCount"    , sideDishList.size());
        requestScope("dessertCount"     , dessertList.size());
        
        requestScope("systemInfo"   , systemInfo);
/*        
        //////////////////////////////////////
        //時間を計る　end
        long end1 = System.currentTimeMillis();
        System.out.println((end1 - start1)  + "ms");
        /////////////////////////////////////////////
*/        
        return forward("index.jsp");
    }
}
