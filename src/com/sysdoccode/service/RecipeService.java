package com.sysdoccode.service;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.sysdoccode.meta.RecipeBodyMeta;
import com.sysdoccode.meta.RecipeHeadMeta;
import com.sysdoccode.model.RecipeBody;
import com.sysdoccode.model.RecipeHead;
import com.sysdoccode.model.SystemInfo;
import com.sysdoccode.util.RecipeException;


/**
 * ���V�s���̃f�[�^�X�g�A�ɃA�N�Z�X����N���X�ł��B
 * 
 * @author Y.Takeuchi
 * @version�@1.0.0
 */
public class RecipeService {
    
    private static final String MEMCHACHE_KEY_LIST              = "LIST_OF_RECIPE_";
    private static final String MEMCHACHE_KEY_LIST_BY_LIMIT     = "LIST_OF_LIMIT_";
    private static final String MEMCHACHE_KEY_LIST_BY_CLASSIC   = "LIST_OF_CLASSIC_";
    
    private static String memchasheKey4RecipeList(Key ancestorKey)
    {
        return MEMCHACHE_KEY_LIST + Datastore.keyToString(ancestorKey);
    }
    
    //�N�G���������ʌ�����
    private static String memchasheKey4RecipeListByLimit(Key ancestorKey)
    {
        return MEMCHACHE_KEY_LIST_BY_LIMIT + Datastore.keyToString(ancestorKey) ;
    }
    
    //�J�e�S����
    private static String memchasheKey4RecipeListByCategory(Key ancestorKey, RecipeHead.CATEGORY category)
    {
        return MEMCHACHE_KEY_LIST + Datastore.keyToString(ancestorKey) + category;
    }
    
    //��ԃ��V�s
    private static String memchasheKey4ClassicRecipes(Key ancestorKey)
    {
        return MEMCHACHE_KEY_LIST_BY_CLASSIC + Datastore.keyToString(ancestorKey);
    }
    
    /**
     * ���V�s�w�b�_ ���擾���܂��B
     * @param headKey �w�b�_��L�[
     * @return ���V�s�w�b�_
     */
    public RecipeHead get(Key headKey) throws RecipeException{
        RecipeHead recipeHead = null;
        recipeHead = Memcache.get(headKey);
        if(recipeHead != null)
        {
            return recipeHead; 
        }
        
        try{
            //�w�肳�ꂽKey�ɊY������G���e�B�e�B������΂����Ԃ��A������� null ��Ԃ��B
            recipeHead = Datastore.getOrNull(RecipeHeadMeta.get(), headKey);
            //Memcache�ɐݒ�
            Memcache.put(headKey, recipeHead);
        }catch(Exception e){
            e.printStackTrace();
            throw new RecipeException(e, "RecipeService.get");
        }
        
        return recipeHead;
    }
    
    
    /**
     * �S���V�s���擾���܂��B
     * @param ancestorKey �V�X�e������L�[
     * @return List<RecipeHead>
     */
    public List<RecipeHead> getAll(Key ancestorKey) throws RecipeException{
        List<RecipeHead> retRecipeList = null;

        try{

            retRecipeList = Memcache.get(memchasheKey4RecipeList(ancestorKey));
            if(retRecipeList != null){
                //System.out.println("RecipeService::: Hit!!Memcache");
                return retRecipeList;
            }
        
            RecipeHeadMeta o = RecipeHeadMeta.get();
            //�f�[�^�X�g�A�ւ̃A�N�Z�X�S���V�s�����擾
            retRecipeList =  Datastore.query(o, ancestorKey)
                    .sort(o.updateDate.desc)//�Í����t�B�[���h�̓\�[�g�T�|�[�g�O
                    .asList();
            
        }catch(Exception e){
            throw new RecipeException(e, "RecipeService.getAll");
        }
        
        Memcache.put(memchasheKey4RecipeList(ancestorKey), retRecipeList);
        
        return retRecipeList;
    }
    
    /**
     * �w�肵���������̃��V�s���擾���܂��B
     * @param ancestorKey �V�X�e������L�[
     * @param limit �N�G�����ʎ擾��
     * @return List<RecipeHead>
     */
    public List<RecipeHead> getAll(Key ancestorKey, int limit) throws RecipeException{
        List<RecipeHead> retRecipeListByLimit = null;

        try{

            retRecipeListByLimit = Memcache.get(memchasheKey4RecipeListByLimit(ancestorKey));
            if(retRecipeListByLimit != null){
                //System.out.println("RecipeService::: Hit!!Memcache");
                return retRecipeListByLimit;
            }
        
            RecipeHeadMeta o = RecipeHeadMeta.get();
            
            //�f�[�^�X�g�A����limit�������̃��V�s���擾
            retRecipeListByLimit =  Datastore.query(o, ancestorKey)
                    .sort(o.updateDate.desc)//�Í����t�B�[���h�̓\�[�g�T�|�[�g�O
                    .limit(limit)
                    .asList();
            
        }catch(Exception e){
            throw new RecipeException(e, "RecipeService.getAll");
        }
        
        Memcache.put(memchasheKey4RecipeListByLimit(ancestorKey), retRecipeListByLimit);
        
        return retRecipeListByLimit;
    }
    
    /**
     * �J�e�S�����Ƃ̃��V�s���擾���܂��B
     * @param ancestorKey �V�X�e������L�[
     * @return List<RecipeHead>
     */
    public List<RecipeHead> getAllByCategory(Key ancestorKey, RecipeHead.CATEGORY category) throws RecipeException{
        List<RecipeHead> retRecipeListBycategory = null;

        try{
            retRecipeListBycategory = Memcache.get(memchasheKey4RecipeListByCategory(ancestorKey, category));
            if(retRecipeListBycategory != null){
                return retRecipeListBycategory;
            }
        
            RecipeHeadMeta o = RecipeHeadMeta.get();
            
            //�f�[�^�X�g�A����Y���̃J�e�S�����V�s���擾
            retRecipeListBycategory =  Datastore.query(o, ancestorKey)
                .filter(o.category.equal(category))
                .sort(o.createDate.desc)
                .asList();
            
        }catch(Exception e){
            throw new RecipeException(e,  "RecipeService.getAllByCategory");
        }
        
        Memcache.put(memchasheKey4RecipeListByCategory(ancestorKey, category), retRecipeListBycategory);
        
        return retRecipeListBycategory;
    }
    
    /**
     * ��ԃ��V�s���擾���܂��B
     * 
     * @param ancestorKey
     * @return ���ׂĂ̒�ԃ��V�s
     */
    public List<RecipeHead> getAllClassicRecipe(Key ancestorKey) throws RecipeException{
        List<RecipeHead> retClassicRecipeList = null;
        
        try{
            retClassicRecipeList = Memcache.get(memchasheKey4ClassicRecipes(ancestorKey));
            if(retClassicRecipeList != null){
                return retClassicRecipeList;
            }
        
            RecipeHeadMeta o = RecipeHeadMeta.get();
            
            //�f�[�^�X�g�A�����ԃ��V�s�����擾
            retClassicRecipeList =  Datastore.query(o, ancestorKey)
                .filter(o.classicRecipe.equal("y"))
                .sort(o.createDate.desc)
                .asList();
            
        }catch(Exception e){
            throw new RecipeException(e , "RecipeService.getAllClassicRecipe");
        }
        
        Memcache.put(memchasheKey4ClassicRecipes(ancestorKey), retClassicRecipeList);
        
        return retClassicRecipeList;
    }

        
    /**
     * ���V�s����o�^���܂��B
     * @param head ���V�s�w�b�_
     * @param body ���V�s�{�f�B
     * @throws Exception
     */
    public Key insert(SystemInfo systemInfo, RecipeHead recipeHead, RecipeBody recipeBody) throws RecipeException {
        
        Transaction tx = Datastore.beginTransaction();
        Key setHeadKey = null;
        
        try {
            //SystemInfo��Key��e�L�[�Ɏw�肵�āARecipeHead��L�[�������̔�
            setHeadKey = Datastore.allocateId(systemInfo.getKey(), RecipeHeadMeta.get());
            recipeHead.setKey(setHeadKey);
        
            //RecipeHead��Key��e�L�[�Ɏw�肵�āABody�̎�L�[�������̔ԂŐ�������B
            recipeBody.setKey(Datastore.allocateId(recipeHead.getKey(), RecipeBodyMeta.get()));
        
            //RecipeHead��RecipeBody���֘A�t������B
            recipeHead.getRecipeBodyRef().setModel(recipeBody);
        
            //RecipeHead��SystemInfo���֘A�t������B
            recipeHead.getSystemInfoRef().setModel(systemInfo);
        
            ///////////////////////
            //Datastore�֑}��
            Datastore.put(tx, recipeHead, recipeBody);
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RecipeException(e, "RecipeService.insert");
        }
        
        //Memcache�̍폜
        Memcache.delete(memchasheKey4RecipeList(systemInfo.getKey()));
        Memcache.delete(memchasheKey4RecipeListByCategory(systemInfo.getKey(), recipeHead.getCategory()));
        Memcache.delete(memchasheKey4RecipeListByLimit(systemInfo.getKey()));
        Memcache.delete(memchasheKey4ClassicRecipes(systemInfo.getKey()));
        
        return setHeadKey;
    }

    /**
     * ���V�s���̏㏑���X�V
     * @param   recipeHead ���V�s���w�b�_
     * @param   recipeBody ���V�s���{�f�B
     * @throws  Exception
     */
    public RecipeHead update(RecipeHead recipeHead, RecipeBody recipeBody) throws RecipeException {
        
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        
        Transaction tx = Datastore.beginTransaction();
        //RecipeHead��SystemInfo���֘A�t������B
        recipeHead.getSystemInfoRef().setModel(systemInfo);
        try {            
            //�X�V���悤�Ƃ��Ă���f�[�^���A���҂ɂ���čX�V����Ă��Ȃ����`�F�b�N����(�y�ϓI�r������)
            Datastore.get(tx, RecipeHeadMeta.get(), recipeHead.getKey(), recipeHead.getVersion());
            //�f�[�^�X�V
            Datastore.put(tx, recipeHead, recipeBody);
            //�R�~�b�g
            tx.commit();
            System.out.println(getClass().getName() + getClass().getMethods());
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RecipeException(e,  "RecipeService.update");
            //throw e;
        }
        
        ////////////////////////
        //Memcache�̍폜
        ///////////////////////
        Memcache.delete(memchasheKey4RecipeList(systemInfo.getKey()));
        Memcache.delete(memchasheKey4RecipeListByCategory(systemInfo.getKey(), recipeHead.getCategory()));
        Memcache.delete(memchasheKey4RecipeListByLimit(systemInfo.getKey()));
        Memcache.delete(memchasheKey4ClassicRecipes(systemInfo.getKey()));
        
        Memcache.delete(recipeHead.getKey());
        
        return get(recipeHead.getKey());
    }

    /**
     * ���V�s���̍폜
     * @param headKey
     * @throws Exception
     */
    public void delete(Key recipeHeadKey) throws RecipeException {
        
        //Memchashe�폜�̂��߂ɁA�\�ߏ����擾���Ă���
        RecipeHead recipeHead = get(recipeHeadKey);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            //�ǂ��z���X�V�Ɋ֌W�Ȃ��폜����悤���{���v���ɉ����Ċy�ϓI�r��������s��
            RecipeHead head = Datastore.get(tx, RecipeHeadMeta.get(), recipeHeadKey);
            Key recipeBodyKey = head.getRecipeBodyRef().getKey();
            Datastore.delete(tx, recipeHeadKey, recipeBodyKey);
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RecipeException(e, "RecipeService.delete");
            //throw e;
        }
        
        ////////////////////////
        //Memcache�̍폜
        ///////////////////////
        Memcache.delete(memchasheKey4RecipeList(recipeHead.getSystemInfoRef().getKey()));
        Memcache.delete(memchasheKey4RecipeListByCategory(recipeHead.getSystemInfoRef().getKey(), recipeHead.getCategory()));
        Memcache.delete(memchasheKey4RecipeListByLimit(recipeHead.getSystemInfoRef().getKey()));
        Memcache.delete(memchasheKey4ClassicRecipes(recipeHead.getSystemInfoRef().getKey()));
        
        Memcache.delete(recipeHead.getKey());
    }

}
