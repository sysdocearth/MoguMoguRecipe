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
 * �V�����V�s���X�g��\������N���X�ł��B
 * �y10���z��\�����܂��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class NewListController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        SystemInfo systeminfo = SystemInfoService.getInstance().get();
        RecipeService recipeService = new RecipeService();
        
        List<RecipeHead> newRecipeList = null;
        try{
            //�V���y10���z�̃��V�s�����擾
            newRecipeList = recipeService.getAll(systeminfo.getKey(), 10);
            
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            RecipeException rex =  new RecipeException(e, "NewListController.run");
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
        }
                
        requestScope("newRecipeList"     , newRecipeList);

        return forward("newlist.jsp");
    }
}
