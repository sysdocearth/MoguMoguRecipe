package com.sysdoccode.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/**
 * URL �g�b�v�y�[�W�ɃA�N�Z�X�����Ƃ��̃N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version�@1.0.0
 */
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        //���_�C���N�g���܂�
        return redirect("/recipe/");
    }
}
