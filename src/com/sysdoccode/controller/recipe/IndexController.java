package com.sysdoccode.controller.recipe;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;

/**
 * ���O�C����̏�����ʂ�\������N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
/*        
        //////////////////////////////////////
        //���Ԃ��v��@start
        long start1 = System.currentTimeMillis();
        /////////////////////////////////////////////
*/        
        //�����ݒ��񂪓o�^����Ă��邩���m�F
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        //�o�^����Ă��Ȃ��ꍇ�́A�ݒ��ʂ�
        if(systemInfo == null){
            return redirect("/setup/");
        }
        
        RecipeService recipeService = new RecipeService();
        //�S���V�s
        List<RecipeHead> recipeAllList = recipeService.getAll(systemInfo.getKey());
        
        //�����̂�����
        List<RecipeHead> meatList       = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.MEAT);
        //�����̂�����
        List<RecipeHead> fishList       = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.FISH);
        //�������̂�����
        List<RecipeHead> eggTofuList    = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.EGGSTOFU);
        //��H�E�`����
        List<RecipeHead> mainSoupList   = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.MAINFSOUP);
        //���H
        List<RecipeHead> sideDishList   = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.SIDEDISH);
        //�f�U�[�g
        List<RecipeHead> dessertList    = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.DESSERT);
        
        //�S���V�s����
        requestScope("allCount"        , recipeAllList.size());
        
        //�J�e�S���ʌ���
        requestScope("meatCount"        , meatList.size());
        requestScope("fishCount"        , fishList.size());
        requestScope("eggTofuCount"     , eggTofuList.size());
        requestScope("mainSoupCount"    , mainSoupList.size());
        requestScope("sideDishCount"    , sideDishList.size());
        requestScope("dessertCount"     , dessertList.size());
        
        requestScope("systemInfo"   , systemInfo);
/*        
        //////////////////////////////////////
        //���Ԃ��v��@end
        long end1 = System.currentTimeMillis();
        System.out.println((end1 - start1)  + "ms");
        /////////////////////////////////////////////
*/        
        return forward("index.jsp");
    }
}
