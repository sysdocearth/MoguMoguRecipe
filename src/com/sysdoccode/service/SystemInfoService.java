package com.sysdoccode.service;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Transaction;
import com.sysdoccode.meta.SystemInfoMeta;
import com.sysdoccode.model.SystemInfo;


/**
 * �V�X�e�����̃f�[�^�X�g�A�ɃA�N�Z�X����N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version�@1.0.0
 */
public class SystemInfoService {
    
    private static final String MEMCHACHE_SYSTEMINFO_KEY = "SINGLE_SYSTEMINFO_KEY";
    private static SystemInfoService instance;

    private SystemInfoService() {
        // �ŏ��̓C���X�^���X�𐶐����Ȃ�
        instance = null;
    }

    public static SystemInfoService getInstance() {
        // ���\�b�h�Ăяo�����������Ƃ��ɁA ���߂ăC���X�^���X�𐶐�
        if (instance == null) {
            instance = new SystemInfoService();
        }
        return instance;
    }

    /**
     * @return�@�V�X�e�����̎擾
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
     * @param isMemcache�@�����L���b�V���𗘗p���邩
     * @return �V�X�e�����̎擾
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
     * �V�X�e������o�^���܂��B
     * 
     * @param systemInfo
     *            �V�X�e�����
     * @throws Exception
     */
    public SystemInfo insert(SystemInfo systemInfo) throws Exception {
        Transaction tx = null;
        try {
            // ��L�[�������̔�
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
     * �V�X�e�����̏㏑���X�V
     * @param   systemInfo �V�X�e�����
     * @throws  Exception
     */
    public void update(SystemInfo systemInfo) throws Exception {
        
        Transaction tx = Datastore.beginTransaction();

        try {            
            //�X�V���悤�Ƃ��Ă���f�[�^���A���҂ɂ���čX�V����Ă��Ȃ����`�F�b�N����(�y�ϓI�r������)
            Datastore.get(tx, SystemInfoMeta.get(), systemInfo.getKey(), systemInfo.getVersion());
            //�f�[�^�X�V
            Datastore.put(tx, systemInfo);
            //�R�~�b�g
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        
        ////////////////////////
        //Memcache�̍폜
        ///////////////////////
        Memcache.delete(MEMCHACHE_SYSTEMINFO_KEY);
        
    }

}
