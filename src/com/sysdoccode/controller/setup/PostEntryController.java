package com.sysdoccode.controller.setup;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.service.SystemInfoService;

/**
 * 設定情報を登録するクラスです。
 * 管理者のみアクセス可能です。
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
            //更新処理
            //////////////////////////////
            //Keyとversionをセットする
            systemInfo.setKey       (nowSystemInfo.getKey());
            systemInfo.setVersion   (nowSystemInfo.getVersion());
            (SystemInfoService.getInstance()).update(systemInfo);
        }else{
            //////////////////////////////
            //新規登録処理
            //////////////////////////////
            (SystemInfoService.getInstance()).insert(systemInfo);
        }
        
        return redirect("/recipe/");
    }
}
