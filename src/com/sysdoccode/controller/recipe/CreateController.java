package com.sysdoccode.controller.recipe;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.RecipeHead.CATEGORY;

/**
 * ���V�s�o�^��ʂ�\������N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class CreateController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        //�J�e�S�����X�g�iselect-box�j
        requestScope("categoryList"     , CATEGORY.values());
        
        return forward("create.jsp");
    }
}
