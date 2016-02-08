package com.sysdoccode.controller.recipe;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.meta.RecipeSearchMeta;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.RecipeSearch;
import com.sysdoccode.service.RecipeService;
import com.sysdoccode.service.SystemInfoService;

/**
 * [Ajax]�@Json�`���̃��f���\���� ���V�s�����ɕԂ��N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class RecipeSearchController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        //�S���V�s�̏��擾
        RecipeService recipeService = new RecipeService();
        List<RecipeHead> recipeHeadList = recipeService.getAll(SystemInfoService.getInstance().get().getKey());
        
        ////////////////////////////////////
        //Json�\���ɂ��邽�� ���f���N���X
        ////////////////////////////////////
        List<RecipeSearch> recipeSearchList = new ArrayList<>();
        
        //label�̓L�[��⃊�X�g
        //value�̓e�L�X�g�{�b�N�X�ɕ\������l
        for(RecipeHead recipeHead : recipeHeadList){
            RecipeSearch recipeSearch = new RecipeSearch();
            recipeSearch.setKey     (recipeHead.getKey());
            recipeSearch.setLabel   (recipeHead.getTitle());
            recipeSearch.setValue   (recipeHead.getTitle());
            recipeSearchList.add(recipeSearch);
        }
        
        // List �^�̌��ʂ��I�u�W�F�N�g�^�̔z��ɕϊ�����
        Object[] recipeSearchArray = recipeSearchList.toArray();
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(RecipeSearchMeta.get().modelsToJson(recipeSearchArray));
        response.flushBuffer();
        return null;
    }
}
