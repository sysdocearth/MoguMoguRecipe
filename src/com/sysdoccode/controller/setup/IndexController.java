package com.sysdoccode.controller.setup;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.SystemInfoService;

/**
 * �ݒ���̉�ʂ�\������N���X�ł��B
 * �Ǘ��҂̂݃A�N�Z�X�\�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        
        if (systemInfo == null){
            requestScope("button_desc"  ,   "�o�^����");
            return forward("index.jsp");
        }
        
        
        requestScope("ownerName"        ,   systemInfo.getOwnerName());
        requestScope("mailAddress"      ,   systemInfo.getMailAddress());
        requestScope("siteConcept"      ,   systemInfo.getSiteConcept());
        requestScope("button_desc"      ,   "�X�V����");
        return forward("index.jsp");
    }
}
