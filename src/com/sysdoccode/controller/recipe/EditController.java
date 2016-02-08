package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ApplicationMessage;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.RecipeHead.CATEGORY;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * ���V�s��ҏW�����ʂ�\������N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class EditController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        RecipeService recipeService = new RecipeService();
        RecipeHead recipeHead = null;
        
        try{
            Key recipeKey = asKey("key");
            recipeHead = recipeService.get(recipeKey);
            
            String password4delete =  StringUtils.rtrim(StringUtils.ltrim(asString("password")));
            
            ///////////////////////////////////////
            //���͂��ꂽ�ҏW�p�p�X���[�h�������������m�F
            if(!(recipeHead.getPassword()).equals(password4delete)){
                errors.put("message", ApplicationMessage.get("error.password"));
                return forward("/recipe/detail?key=" + recipeHead.getKey());                
            }
            
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //Key�̎w�肪�s��
            return forward("/recipe/error/notfound-error.jsp");
        }
        
        //���V�s�w�b�_���
        requestScope("recipeHead"       , recipeHead);
        //�X�̃X�R�[�v
        requestScope("title"            , recipeHead.getTitle());
        requestScope("introduction"     , recipeHead.getIntroduction());
        requestScope("minute"           , recipeHead.getMinute());
        requestScope("servings"         , recipeHead.getServings());
        requestScope("directions"       , recipeHead.getRecipeBodyRef().getModel().getDirections());
        requestScope("ingredients"      , recipeHead.getRecipeBodyRef().getModel().getIngredients());
        requestScope("tips"             , recipeHead.getRecipeBodyRef().getModel().getTips());
        requestScope("familyComment"    , recipeHead.getRecipeBodyRef().getModel().getFamilyComment());
        requestScope("author"           , recipeHead.getAuthor());
        requestScope("password"         , recipeHead.getPassword());
        requestScope("noRatings"        , recipeHead.getNoRatings());
        requestScope("classicRecipe"    , recipeHead.getClassicRecipe());
        
        //�J�e�S�����X�g�iselect-box�j
        requestScope("categoryList"     , CATEGORY.values());
        //�p���������X�g �\���p
        requestScope("pankzbefore"      , recipeHead.getCategory().getStrValue());
        
        return forward("edit.jsp");
    }
}
