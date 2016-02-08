package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
//import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;



//import com.google.appengine.api.datastore.Key;
import com.sysdoccode.model.RecipeBody;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * ���V�s�̐V�K�o�^������N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class PostEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        RecipeHead recipeHead = new RecipeHead();
        BeanUtil.copy(request, recipeHead);
        
        RecipeBody recipeBody = new RecipeBody();
        BeanUtil.copy(request, recipeBody);
        
        RecipeService recipeService = new RecipeService();
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        
        //�ЂƂ܂������o�^�i�]���Ȃ��j
        String check_invalid = param("check_invalid");
        if(!StringUtils.isEmpty(check_invalid)){
            recipeHead.setNoRatings("y");
        }
        
        //��ԃ��V�s�̓o�^
        String check_classic = param("check_classic");
        if(!StringUtils.isEmpty(check_classic)){
            recipeHead.setClassicRecipe("y");
        }
        
        /////////////////////////////////
        //���V�s��o�^
        try{
            recipeService.insert(systemInfo, recipeHead, recipeBody);
        
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }
                
        return redirect("/recipe/list?complete=1&kind=" + recipeHead.getCategory());
    }
}
