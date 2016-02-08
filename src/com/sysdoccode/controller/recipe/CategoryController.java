package com.sysdoccode.controller.recipe;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

//import com.google.appengine.api.users.User;
//import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.sysdoccode.meta.ActivityInfoMeta;
import com.sysdoccode.model.ActivityInfo;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;

/**
 * [Ajax]　Json形式のモデル構造をTop画面に返すクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class CategoryController extends Controller {

    
//    private final UserService userService = UserServiceFactory.getUserService();

    @Override
    public Navigation run() throws Exception {
        
        ////////////////////////////////////
        //Json構造にするため モデルクラス
        ////////////////////////////////////
        ActivityInfo activityInfo = new ActivityInfo();
        
        //初期設定情報が登録されているかを確認
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        //登録されていない場合は、設定画面へ
        if(systemInfo == null){
            return redirect("/setup/");
        }
        
        RecipeService recipeService = new RecipeService();
        
        //////////////////////////////////
        //レシピ カテゴリ別の件数を取得
        /////////////////////////////////
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
        //副菜
        List<RecipeHead> sideDishList   = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.SIDEDISH);
        //デザート
        List<RecipeHead> dessertList    = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.DESSERT);

        ////////////////////////////////////
        //モデルクラスに情報をセット
        ////////////////////////////////////
        //Googleアカウント
        activityInfo.setAccountId(UserServiceFactory.getUserService().getCurrentUser().getEmail());
        //レシピ全件数
        activityInfo.setAllCount(recipeAllList.size());
        //お肉のおかずレシピ件数
        activityInfo.setMeatCount(meatList.size());
        //お魚のレシピ件数
        activityInfo.setFishCount(fishList.size());
        //卵とお豆腐のレシピ件数
        activityInfo.setEggTofuCount(eggTofuList.size());
        //主食・汁物のレシピ件数
        activityInfo.setMainSoupCount(mainSoupList.size());
        //副菜のレシピ件数
        activityInfo.setSideDishCount(sideDishList.size());
        //デザートレシピ件数
        activityInfo.setDessertCount(dessertList.size());
        
        //サイトコンセプト
        activityInfo.setSiteConcept(systemInfo.getSiteConcept());
        
        //ログアウトURL
        activityInfo.setLogoutURL(UserServiceFactory.getUserService().createLogoutURL("/recipe/index.html"));
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(ActivityInfoMeta.get().modelToJson(activityInfo));
        response.flushBuffer();
        
        return null;
    }

}
