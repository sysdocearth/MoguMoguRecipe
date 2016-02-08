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
 * �J�e�S���ʂ̃��V�s���X�g��\������N���X�ł��B
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
            
            //�J�e�S�����Ƃ̃��V�s�����擾
            recipeListByCategory = recipeService.getAllByCategory(systeminfo.getKey(), category);
            
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //kind�̎w�肪�s��
            return forward("/recipe/error/notfound-error.jsp");
        }
        
        ////////////////////////////////////////////////////
        //���݂��Ȃ�Key���w�肳�ꂽ�ꍇ��recipeListByCategory��Null
        if(recipeListByCategory==null){
            return forward("/recipe/error.jsp");
        }
                
        requestScope("categoryDesc"     , category.getStrValue());
        requestScope("imgFileName"      , category.getImgFileName());
        requestScope("categoryList"     , recipeListByCategory);
        
        return forward("list.jsp");
    }
}
