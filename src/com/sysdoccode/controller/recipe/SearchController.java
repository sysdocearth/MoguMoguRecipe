package com.sysdoccode.controller.recipe;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;
import com.sysdoccode.util.RecipeException;
import com.sysdoccode.util.StringUtils;

/**
 * �����o�[�ł̃^�C�g���ꕔ�����񌟍��̌��ʂ�Ԃ��N���X�ł��B
 * 
 * @author Y.Takeuchi
 *�@@version 1.0.0
 */
public class SearchController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        
        String searchTitle = null; //����������
        List<RecipeHead> hitRecipeList = null; //������������܂ރ��V�s���
        try{
            //���p�X�y�[�X�̃g����
            searchTitle = StringUtils.rtrim(StringUtils.ltrim(param("title")));
            //�S�p�X�y�[�X�̃g����
            searchTitle = StringUtils.rtrim(StringUtils.ltrim(searchTitle, StringUtils.ZENKAKUSPACE), StringUtils.ZENKAKUSPACE);
            
            //�������͂���Ă��Ȃ��ꍇ�́A�g�b�v�y�[�W�ցi�X�y�[�X���g�����������ʂ��܂ށj
            if(StringUtils.isBlank(searchTitle)){
                return forward("/recipe/error/notfound-search.jsp");
            }
            
            RecipeService recipeService = new RecipeService();
            List<RecipeHead> recipeHeadAll = recipeService.getAll(SystemInfoService.getInstance().get().getKey());
            
            hitRecipeList = search(recipeHeadAll, searchTitle);
            
        }catch(RecipeException rex){
                //�f�[�^�X�g�A�A�N�Z�X�ɂ��G���[
                System.err.println(rex.toString());
                requestScope("errormessage" , rex.toString());
                return forward("/recipe/error/server-error.jsp");
            
        }catch(Exception e){
            if(searchTitle==null){
                return forward("/recipe/error/notfound-search.jsp");
            }
        }
        
        requestScope("hitRecipeList"    , hitRecipeList);
        requestScope("searchTitle"      , searchTitle);

        return forward("search.jsp");
    }
    
    /**
     * ������������܂ރ��V�s����Ԃ��܂��B
     * 
     * @param allRecipeHead ���V�s�̑S���
     * @param searchTitle�@����������
     * @return�@������������܂ރ��V�s���
     */
    private List<RecipeHead> search(List<RecipeHead> allRecipeHead, String searchTitle){
        List<RecipeHead> retRecipeHeadList = new ArrayList<>();
        
        for(RecipeHead recipeHead : allRecipeHead){
            String recipeTitle = recipeHead.getTitle();
            
            if(StringUtils.contains(recipeTitle, searchTitle))
            {
                retRecipeHeadList.add(recipeHead);
            }            
        }
        
        return retRecipeHeadList;
    }
}
