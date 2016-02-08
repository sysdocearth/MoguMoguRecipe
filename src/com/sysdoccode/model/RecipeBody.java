package com.sysdoccode.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.meta.RecipeHeadMeta;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelRef;
import org.slim3.datastore.Model;

/**
 * レシピボディ情報のクラス
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
@Model(schemaVersion = 1)
public class RecipeBody implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** Kcal */
    private String kcal;
    
    /** 材料 */
    private String ingredients;
    
    /** 作り方 */
    private String directions;
    
    /** 作り方のコツ */
    private String tips;
    
    /** 家族コメント */
    private String familyComment;
    
    /** RecipeHeader への1対1の関連 */
    @Attribute(persistent = false)
    private InverseModelRef<RecipeHead, RecipeBody> recipeHeadRef =
            new InverseModelRef<RecipeHead, RecipeBody>(RecipeHead.class, RecipeHeadMeta.get().recipeBodyRef, this);


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
     * @return kcal
     */
    public String getKcal() {
        return kcal;
    }

    /**
     * @param kcal セットする kcal
     */
    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    /**
     * @return 材料
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients 材料
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return 作り方
     */
    public String getDirections() {
        return directions;
    }

    /**
     * @param directions 作り方
     */
    public void setDirections(String directions) {
        this.directions = directions;
    }

    /**
     * @return 作り方のコツ
     */
    public String getTips() {
        return tips;
    }

    /**
     * @param tips 作り方のコツ
     */
    public void setTips(String tips) {
        this.tips = tips;
    }

    /**
     * @return 家族コメント
     */
    public String getFamilyComment() {
        return familyComment;
    }

    /**
     * @param familyComment 家族コメント
     */
    public void setFamilyComment(String familyComment) {
        this.familyComment = familyComment;
    }
    
    /**
     * @return 参照元情報 RecipeHeader を取得
     */
    public InverseModelRef<RecipeHead, RecipeBody> getRecipeHeadRef() {
        return recipeHeadRef;
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
        RecipeBody other = (RecipeBody) obj;
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
