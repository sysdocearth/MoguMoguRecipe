package com.sysdoccode.controller.setup;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.SystemInfoService;

/**
 * 設定情報の画面を表示するクラスです。
 * 管理者のみアクセス可能です。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        
        if (systemInfo == null){
            requestScope("button_desc"  ,   "登録する");
            return forward("index.jsp");
        }
        
        
        requestScope("ownerName"        ,   systemInfo.getOwnerName());
        requestScope("mailAddress"      ,   systemInfo.getMailAddress());
        requestScope("siteConcept"      ,   systemInfo.getSiteConcept());
        requestScope("button_desc"      ,   "更新する");
        return forward("index.jsp");
    }
}
