package com.sysdoccode.service;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Transaction;
import com.sysdoccode.meta.SystemInfoMeta;
import com.sysdoccode.model.SystemInfo;


/**
 * システム情報のデータストアにアクセスするクラスです。
 * 
 * @author Y.Takeuchi
 * @version　1.0.0
 */
public class SystemInfoService {
    
    private static final String MEMCHACHE_SYSTEMINFO_KEY = "SINGLE_SYSTEMINFO_KEY";
    private static SystemInfoService instance;

    private SystemInfoService() {
        // 最初はインスタンスを生成しない
        instance = null;
    }

    public static SystemInfoService getInstance() {
        // メソッド呼び出しがあったときに、 初めてインスタンスを生成
        if (instance == null) {
            instance = new SystemInfoService();
        }
        return instance;
    }

    /**
     * @return　システム情報の取得
     */
    public SystemInfo get() {
        SystemInfo systemInfo = null;
        
        systemInfo = Memcache.get(MEMCHACHE_SYSTEMINFO_KEY);
        if (systemInfo != null) {
            return systemInfo;
        }
        
        SystemInfoMeta meta = SystemInfoMeta.get();
        systemInfo = Datastore.query(meta).asSingle();
        
        Memcache.put(MEMCHACHE_SYSTEMINFO_KEY, systemInfo);

        return systemInfo;
    }
    
    /**
     * @param isMemcache　メムキャッシュを利用するか
     * @return システム情報の取得
     */
    public SystemInfo get(boolean isMemcache) {
        SystemInfo systemInfo = null;
        
        if (isMemcache){
            systemInfo = Memcache.get(MEMCHACHE_SYSTEMINFO_KEY);
            if (systemInfo != null) {
                return systemInfo;
            }
        }
        
        SystemInfoMeta meta = SystemInfoMeta.get();
        systemInfo = Datastore.query(meta).asSingle();
        
        Memcache.put(MEMCHACHE_SYSTEMINFO_KEY, systemInfo);

        return systemInfo;
    }
    
    /**
     * システム情報を登録します。
     * 
     * @param systemInfo
     *            システム情報
     * @throws Exception
     */
    public SystemInfo insert(SystemInfo systemInfo) throws Exception {
        Transaction tx = null;
        try {
            // 主キーを自動採番
            systemInfo.setKey(Datastore.allocateId(SystemInfoMeta.get()));

            tx = Datastore.beginTransaction();
            Datastore.put(tx, systemInfo);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }

        return systemInfo;
    }
    
    /**
     * システム情報の上書き更新
     * @param   systemInfo システム情報
     * @throws  Exception
     */
    public void update(SystemInfo systemInfo) throws Exception {
        
        Transaction tx = Datastore.beginTransaction();

        try {            
            //更新しようとしているデータが、他者によって更新されていないかチェックする(楽観的排他制御)
            Datastore.get(tx, SystemInfoMeta.get(), systemInfo.getKey(), systemInfo.getVersion());
            //データ更新
            Datastore.put(tx, systemInfo);
            //コミット
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        
        ////////////////////////
        //Memcacheの削除
        ///////////////////////
        Memcache.delete(MEMCHACHE_SYSTEMINFO_KEY);
        
    }

}
