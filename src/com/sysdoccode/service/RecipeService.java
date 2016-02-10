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
 * レシピ情報のデータストアにアクセスするクラスです。
 * 
 * @author Y.Takeuchi
 * @version　1.0.0
 */
public class RecipeService {
    
    private static final String MEMCHACHE_KEY_LIST              = "LIST_OF_RECIPE_";
    private static final String MEMCHACHE_KEY_LIST_BY_LIMIT     = "LIST_OF_LIMIT_";
    private static final String MEMCHACHE_KEY_LIST_BY_CLASSIC   = "LIST_OF_CLASSIC_";
    
    private static String memchasheKey4RecipeList(Key ancestorKey)
    {
        return MEMCHACHE_KEY_LIST + Datastore.keyToString(ancestorKey);
    }
    
    //クエリ検索結果件数別
    private static String memchasheKey4RecipeListByLimit(Key ancestorKey)
    {
        return MEMCHACHE_KEY_LIST_BY_LIMIT + Datastore.keyToString(ancestorKey) ;
    }
    
    //カテゴリ別
    private static String memchasheKey4RecipeListByCategory(Key ancestorKey, RecipeHead.CATEGORY category)
    {
        return MEMCHACHE_KEY_LIST + Datastore.keyToString(ancestorKey) + category;
    }
    
    //定番レシピ
    private static String memchasheKey4ClassicRecipes(Key ancestorKey)
    {
        return MEMCHACHE_KEY_LIST_BY_CLASSIC + Datastore.keyToString(ancestorKey);
    }
    
    /**
     * レシピヘッダ を取得します。
     * @param headKey ヘッダ主キー
     * @return レシピヘッダ
     */
    public RecipeHead get(Key headKey) throws RecipeException{
        RecipeHead recipeHead = null;
        recipeHead = Memcache.get(headKey);
        if(recipeHead != null)
        {
            return recipeHead; 
        }
        
        try{
            //指定されたKeyに該当するエンティティがあればそれを返し、無ければ null を返す。
            recipeHead = Datastore.getOrNull(RecipeHeadMeta.get(), headKey);
            //Memcacheに設定
            Memcache.put(headKey, recipeHead);
        }catch(Exception e){
            e.printStackTrace();
            throw new RecipeException(e, "RecipeService.get");
        }
        
        return recipeHead;
    }
    
    
    /**
     * 全レシピを取得します。
     * @param ancestorKey システム情報主キー
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
            //データストアへのアクセス全レシピ情報を取得
            retRecipeList =  Datastore.query(o, ancestorKey)
                    .sort(o.updateDate.desc)//暗号化フィールドはソートサポート外
                    .asList();
            
        }catch(Exception e){
            throw new RecipeException(e, "RecipeService.getAll");
        }
        
        Memcache.put(memchasheKey4RecipeList(ancestorKey), retRecipeList);
        
        return retRecipeList;
    }
    
    /**
     * 指定した件数分のレシピを取得します。
     * @param ancestorKey システム情報主キー
     * @param limit クエリ結果取得数
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
            
            //データストアからlimit件数分のレシピ情報取得
            retRecipeListByLimit =  Datastore.query(o, ancestorKey)
                    .sort(o.updateDate.desc)//暗号化フィールドはソートサポート外
                    .limit(limit)
                    .asList();
            
        }catch(Exception e){
            throw new RecipeException(e, "RecipeService.getAll");
        }
        
        Memcache.put(memchasheKey4RecipeListByLimit(ancestorKey), retRecipeListByLimit);
        
        return retRecipeListByLimit;
    }
    
    /**
     * カテゴリごとのレシピを取得します。
     * @param ancestorKey システム情報主キー
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
            
            //データストアから該当のカテゴリレシピを取得
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
     * 定番レシピを取得します。
     * 
     * @param ancestorKey
     * @return すべての定番レシピ
     */
    public List<RecipeHead> getAllClassicRecipe(Key ancestorKey) throws RecipeException{
        List<RecipeHead> retClassicRecipeList = null;
        
        try{
            retClassicRecipeList = Memcache.get(memchasheKey4ClassicRecipes(ancestorKey));
            if(retClassicRecipeList != null){
                return retClassicRecipeList;
            }
        
            RecipeHeadMeta o = RecipeHeadMeta.get();
            
            //データストアから定番レシピ情報を取得
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
     * レシピ情報を登録します。
     * @param head レシピヘッダ
     * @param body レシピボディ
     * @throws Exception
     */
    public Key insert(SystemInfo systemInfo, RecipeHead recipeHead, RecipeBody recipeBody) throws RecipeException {
        
        Transaction tx = Datastore.beginTransaction();
        Key setHeadKey = null;
        
        try {
            //SystemInfoのKeyを親キーに指定して、RecipeHead主キーを自動採番
            setHeadKey = Datastore.allocateId(systemInfo.getKey(), RecipeHeadMeta.get());
            recipeHead.setKey(setHeadKey);
        
            //RecipeHeadのKeyを親キーに指定して、Bodyの主キーも自動採番で生成する。
            recipeBody.setKey(Datastore.allocateId(recipeHead.getKey(), RecipeBodyMeta.get()));
        
            //RecipeHeadにRecipeBodyを関連付けする。
            recipeHead.getRecipeBodyRef().setModel(recipeBody);
        
            //RecipeHeadにSystemInfoを関連付けする。
            recipeHead.getSystemInfoRef().setModel(systemInfo);
        
            ///////////////////////
            //Datastoreへ挿入
            Datastore.put(tx, recipeHead, recipeBody);
            tx.commit();
        }
        catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RecipeException(e, "RecipeService.insert");
        }
        
        //Memcacheの削除
        Memcache.delete(memchasheKey4RecipeList(systemInfo.getKey()));
        Memcache.delete(memchasheKey4RecipeListByCategory(systemInfo.getKey(), recipeHead.getCategory()));
        Memcache.delete(memchasheKey4RecipeListByLimit(systemInfo.getKey()));
        Memcache.delete(memchasheKey4ClassicRecipes(systemInfo.getKey()));
        
        return setHeadKey;
    }

    /**
     * レシピ情報の上書き更新
     * @param   recipeHead レシピ情報ヘッダ
     * @param   recipeBody レシピ情報ボディ
     * @throws  Exception
     */
    public RecipeHead update(RecipeHead recipeHead, RecipeBody recipeBody) throws RecipeException {
        
        SystemInfo systemInfo = SystemInfoService.getInstance().get();
        
        Transaction tx = Datastore.beginTransaction();
        //RecipeHeadにSystemInfoを関連付けする。
        recipeHead.getSystemInfoRef().setModel(systemInfo);
        try {            
            //更新しようとしているデータが、他者によって更新されていないかチェックする(楽観的排他制御)
            Datastore.get(tx, RecipeHeadMeta.get(), recipeHead.getKey(), recipeHead.getVersion());
            //データ更新
            Datastore.put(tx, recipeHead, recipeBody);
            //コミット
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
        //Memcacheの削除
        ///////////////////////
        Memcache.delete(memchasheKey4RecipeList(systemInfo.getKey()));
        Memcache.delete(memchasheKey4RecipeListByCategory(systemInfo.getKey(), recipeHead.getCategory()));
        Memcache.delete(memchasheKey4RecipeListByLimit(systemInfo.getKey()));
        Memcache.delete(memchasheKey4ClassicRecipes(systemInfo.getKey()));
        
        Memcache.delete(recipeHead.getKey());
        
        return get(recipeHead.getKey());
    }

    /**
     * レシピ情報の削除
     * @param headKey
     * @throws Exception
     */
    public void delete(Key recipeHeadKey) throws RecipeException {
        
        //Memchashe削除のために、予め情報を取得しておく
        RecipeHead recipeHead = get(recipeHeadKey);
        
        Transaction tx = Datastore.beginTransaction();
        try {
            //追い越し更新に関係なく削除するよう実施※要件に応じて楽観的排他制御を行う
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
        //Memcacheの削除
        ///////////////////////
        Memcache.delete(memchasheKey4RecipeList(recipeHead.getSystemInfoRef().getKey()));
        Memcache.delete(memchasheKey4RecipeListByCategory(recipeHead.getSystemInfoRef().getKey(), recipeHead.getCategory()));
        Memcache.delete(memchasheKey4RecipeListByLimit(recipeHead.getSystemInfoRef().getKey()));
        Memcache.delete(memchasheKey4ClassicRecipes(recipeHead.getSystemInfoRef().getKey()));
        
        Memcache.delete(recipeHead.getKey());
    }

}
