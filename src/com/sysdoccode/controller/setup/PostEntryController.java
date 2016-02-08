package com.sysdoccode.controller.setup;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.SystemInfoService;

/**
 * �ݒ����o�^����N���X�ł��B
 * �Ǘ��҂̂݃A�N�Z�X�\�ł��B
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class PostEntryController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        SystemInfo systemInfo = new SystemInfo();
        BeanUtil.copy(request, systemInfo);
        
        SystemInfo nowSystemInfo = SystemInfoService.getInstance().get(false);
        
        if(nowSystemInfo != null){
            ///////////////////////////////
            //�X�V����
            //////////////////////////////
            //Key��version���Z�b�g����
            systemInfo.setKey       (nowSystemInfo.getKey());
            systemInfo.setVersion   (nowSystemInfo.getVersion());
            (SystemInfoService.getInstance()).update(systemInfo);
        }else{
            //////////////////////////////
            //�V�K�o�^����
            //////////////////////////////
            (SystemInfoService.getInstance()).insert(systemInfo);
        }
        
        return redirect("/recipe/");
    }
}
