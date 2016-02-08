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
 * [Ajax]�@Json�`���̃��f���\����Top��ʂɕԂ��N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class CategoryController extends Controller {

    
//    private final UserService userService = UserServiceFactory.getUserService();

    @Override
    public Navigation run() throws Exception {
        
        ////////////////////////////////////
        //Json�\���ɂ��邽�� ���f���N���X
        ////////////////////////////////////
        ActivityInfo activityInfo = new ActivityInfo();
        
        //�����ݒ��񂪓o�^����Ă��邩���m�F
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        //�o�^����Ă��Ȃ��ꍇ�́A�ݒ��ʂ�
        if(systemInfo == null){
            return redirect("/setup/");
        }
        
        RecipeService recipeService = new RecipeService();
        
        //////////////////////////////////
        //���V�s �J�e�S���ʂ̌������擾
        /////////////////////////////////
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
        //����
        List<RecipeHead> sideDishList   = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.SIDEDISH);
        //�f�U�[�g
        List<RecipeHead> dessertList    = recipeService.getAllByCategory(systemInfo.getKey(), RecipeHead.CATEGORY.DESSERT);

        ////////////////////////////////////
        //���f���N���X�ɏ����Z�b�g
        ////////////////////////////////////
        //Google�A�J�E���g
        activityInfo.setAccountId(UserServiceFactory.getUserService().getCurrentUser().getEmail());
        //���V�s�S����
        activityInfo.setAllCount(recipeAllList.size());
        //�����̂��������V�s����
        activityInfo.setMeatCount(meatList.size());
        //�����̃��V�s����
        activityInfo.setFishCount(fishList.size());
        //���Ƃ������̃��V�s����
        activityInfo.setEggTofuCount(eggTofuList.size());
        //��H�E�`���̃��V�s����
        activityInfo.setMainSoupCount(mainSoupList.size());
        //���؂̃��V�s����
        activityInfo.setSideDishCount(sideDishList.size());
        //�f�U�[�g���V�s����
        activityInfo.setDessertCount(dessertList.size());
        
        //�T�C�g�R���Z�v�g
        activityInfo.setSiteConcept(systemInfo.getSiteConcept());
        
        //���O�A�E�gURL
        activityInfo.setLogoutURL(UserServiceFactory.getUserService().createLogoutURL("/recipe/index.html"));
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(ActivityInfoMeta.get().modelToJson(activityInfo));
        response.flushBuffer();
        
        return null;
    }

}
