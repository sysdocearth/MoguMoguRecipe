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
 * �o�^�������V�s��ύX�X�V����N���X�ł��B
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
        //�@�X�V�O�̃��V�s�����擾
        /////////////////////////////////
        Key headKey = null;
        try{
            headKey = asKey("key");
                        
            recipeHead = recipeService.get(headKey);
            recipeBody = recipeHead.getRecipeBodyRef().getModel();
        
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //Key���s���ȏꍇ
            return forward("/recipe/error/notfound-error.jsp");
        }
                
        /////////////////////////////////
        // Key �� version �̑ޔ�
        Key  orgRecipeHeadKey        = recipeHead.getKey();
        Long orgRecipeHeadVersion    = recipeHead.getVersion();
        Key  orgRecipeBodyKey        = recipeHead.getRecipeBodyRef().getModel().getKey();
        Long orgRecipeBodyVersion    = recipeHead.getRecipeBodyRef().getModel().getVersion();
        
        BeanUtil.copy(request, recipeHead);
        BeanUtil.copy(request, recipeBody);
        
        /////////////////////////////////
        // Key �� Version �̍Đݒ�
        recipeHead.setKey       (orgRecipeHeadKey);
        recipeHead.setVersion   (orgRecipeHeadVersion);
        recipeBody.setKey       (orgRecipeBodyKey);
        recipeBody.setVersion   (orgRecipeBodyVersion);
        
        //�ЂƂ܂������o�^�i�]���Ȃ��j
        String check_invalid = param("check_invalid");
        if(!StringUtils.isEmpty(check_invalid)){
            recipeHead.setNoRatings("y");
        }else{
            recipeHead.setNoRatings("");
        }
        
        //��ԃ��V�s�̓o�^
        String check_classic = param("check_classic");
        if(!StringUtils.isEmpty(check_classic)){
            recipeHead.setClassicRecipe("y");
        }else{
            recipeHead.setClassicRecipe("");
        }
        
        /////////////////////////////////
        //�@���V�s�����X�V����
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
