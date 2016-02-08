package com.sysdoccode.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.ModificationDate;

/**
 * レシピヘッダー情報のクラス
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
@Model(schemaVersion = 1)
public class RecipeHead implements Serializable {

    private static final long serialVersionUID = 1L;
    
    ///////////////////////
    // レシピカテゴリ
    ///////////////////////
    public enum CATEGORY {
        MEAT            (0      ,"主菜：（お肉）のおかず"               ,"/img/meat.png"  )
        , FISH          (1      ,"主菜：（お魚）のおかず"               ,"/img/fish.png")
        , EGGSTOFU      (2      ,"主菜：（卵・豆腐）のおかず"            ,"/img/egg.png")
        , MAINFSOUP     (3      ,"主食・汁もの：お米・パスタ・スープ など"    ,"/img/soop.png")
        , SIDEDISH      (4      ,"副菜：野菜・きのこのおかず"            ,"/img/carrots.png")
        , DESSERT       (5      ,"愛情の一品：デザート・お菓子 など"       ,"/img/cake.png")
        ;
        
        private int intValue;
        private String strValue;
        private String imgFileName;
        
        CATEGORY(final int kind, final String desc, final String fileName){
            intValue        = kind ;
            strValue        = desc ;
            imgFileName     = fileName;
        }
        
        // enum定数から整数へ変換
        public int getIntValue() {
            return intValue;
        }
        
        // enum定数から文字列へ変換
        public String getStrValue() {
            return strValue;
        }
        
        public String getImgFileName() {
            return imgFileName;
        }
        
        // 整数からenum定数へ変換
        public static CATEGORY valueOf(final int anIntValue) {
            for (CATEGORY d : values()) {
                if (d.getIntValue() == anIntValue) {
                    return d;
                }
            }
            return null;
        }
        
    }

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** レシピタイトル */
    private String title;
    
    /** レシピカテゴリー */
    private CATEGORY category;
    
    /** 紹介コメント　*/
    private String introduction;
    
    /** もぐもぐレベル (1～5)*/
    private int mogumoguLevel = 1;
    
    /** メモ登録（まだ料理していない場合:"y"）*/
    private String noRatings;
    
    /** 作成時間（分） */
    private String minute;
  
    /** 人前 */
    private String servings;
    
    /** ペンネーム*/
    private String author;
    
    /** 編集用パスワード*/
    @Attribute(cipher=true)
    private String password;
    
    /** 定番レシピ(定番レシピの場合："y") */
    private String classicRecipe;
    
    /** レシピ作成日 */
    @Attribute(listener = CreationDate.class)
    private Date createDate;
    
    /** レシピ更新日 */
    @Attribute(listener = ModificationDate.class)
    private Date updateDate;
    
    /** SystemInfo への多対1関連の外部キー  */
    private ModelRef<SystemInfo> systemInfoRef = new ModelRef<SystemInfo>(SystemInfo.class);
    
    /** RecipeBodyへの1対1の関連  */
    private ModelRef<RecipeBody> recipeBodyRef = new ModelRef<RecipeBody>(RecipeBody.class);
    
    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return レシピタイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title レシピタイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return レシピカテゴリ
     */
    public CATEGORY getCategory() {
        return category;
    }

    /**
     * @param category レシピカテゴリ
     */
    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    /**
     * @return レシピ紹介文
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction レシピ紹介文
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return もぐもぐレベル（1～5）
     */
    public int getMogumoguLevel() {
        return mogumoguLevel;
    }

    /**
     * @param mogumoguLevel もぐもぐレベル（1～5）
     */
    public void setMogumoguLevel(int mogumoguLevel) {
        this.mogumoguLevel = mogumoguLevel;
    }

    /**
     * @return メモ登録（まだ料理していない場合:y）
     */
    public String getNoRatings() {
        return noRatings;
    }

    /**
     * @param メモ登録（まだ料理していない場合:y） 
     */
    public void setNoRatings(String noRatings) {
        this.noRatings = noRatings;
    }

    /**
     * @return 作成時間（分） 
     */
    public String getMinute() {
        return minute;
    }

    /**
     * @param 作成時間（分） 
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * @return 人前
     */
    public String getServings() {
        return servings;
    }

    /**
     * @param servings 人前
     */
    public void setServings(String servings) {
        this.servings = servings;
    }

    /**
     * @return ペンネーム
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author ペンネーム
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return 編集用パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 編集用パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return classicRecipe
     */
    public String getClassicRecipe() {
        return classicRecipe;
    }

    /**
     * @param classicRecipe セットする classicRecipe
     */
    public void setClassicRecipe(String classicRecipe) {
        this.classicRecipe = classicRecipe;
    }

    /**
     * @return レシピ作成日
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate レシピ作成日
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return レシピ更新日
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate レシピ更新日
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return systemInfoRef 参照先である SystemInfo情報の参照
     */
    public ModelRef<SystemInfo> getSystemInfoRef() {
        return systemInfoRef;
    }

    /**
     * @return recipeBodyRef　参照先であるRecipeBody情報の取得
     */
    public ModelRef<RecipeBody> getRecipeBodyRef() {
        return recipeBodyRef;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RecipeHead other = (RecipeHead) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
