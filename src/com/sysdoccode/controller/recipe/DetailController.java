package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.util.RecipeException;

/**
 * ���V�s�ڍ׏���\������N���X�ł�
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class DetailController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        RecipeService recipeService = new RecipeService();
        RecipeHead recipeHead = null;
        try{
            Key recipeKey = asKey("key");
            
            //URL��Key�̎w�肪�Ȃ�
            if(recipeKey == null){
                return forward("/recipe/error/notfound-error.jsp");
            }
            
            recipeHead = recipeService.get(recipeKey);
            
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
                    
        }catch(Exception e){
            //Key���s���̏ꍇ
            return forward("/recipe/error/notfound-error.jsp");
        }
        
        ////////////////////////////////////////////////////
        //���݂��Ȃ�Key���w�肳�ꂽ�ꍇ��recipeHead��Null
        if(recipeHead==null){
            return forward("/recipe/error.jsp");
        }
        
        requestScope("recipeHead"       , recipeHead);
        requestScope("recipeBody"       , recipeHead.getRecipeBodyRef().getModel());
        requestScope("pankzbefore"      , recipeHead.getCategory().getStrValue());
        
        return forward("detail.jsp");
    }
}
