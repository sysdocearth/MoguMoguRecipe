package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ApplicationMessage;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * �o�^�����Ă��郌�V�s���폜����N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class DeleteEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {

        RecipeHead recipeHead = null;
        RecipeService recipeService = new RecipeService();
        
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
            
            ///////////////////////////////////////
            //�Ώۂ̃��V�s���폜����
            recipeService.delete(asKey("key"));
            
        }catch(RecipeException rex){
            //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
            System.err.println(rex.toString());
            requestScope("errormessage" , rex.toString());
            return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            //kind�̎w�肪�s��
            return forward("/recipe/error/notfound-error.jsp");
        }
        
        return redirect("/recipe/list?kind=" + recipeHead.getCategory() + "&complete=3" );
    }
}
