package com.sysdoccode.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.sysdoccode.meta.RecipeHeadMeta;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

/**
 * システム情報のクラス
 * 
 * @author Y.Takeuchi
 *　@version 1.0.0
 */
@Model(schemaVersion = 1)
public class SystemInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** オーナー名前 */
    private String ownerName;
    
    /** 連絡先メールアドレス*/
    private String mailAddress;
    
    /** サイトコンセプト */
    private String siteConcept;
    
    /** サービス開始日 */
    @Attribute(listener = CreationDate.class)
    private Date startDate;
    
    /** RecipeHeaderへの双方向1対多関連 */
    @Attribute(persistent = false)
    private InverseModelListRef<RecipeHead, SystemInfo> recipeHeadListRef = 
        new InverseModelListRef<RecipeHead, SystemInfo>(RecipeHead.class, RecipeHeadMeta.get().systemInfoRef.getName() ,this);

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
     * @return オーナー名前
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName オーナー名前
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return 連絡先メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param mailAddress 連絡先メールアドレス
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * @return サイトコンセプト
     */
    public String getSiteConcept() {
        return siteConcept;
    }

    /**
     * @param siteConcept サイトコンセプト
     */
    public void setSiteConcept(String siteConcept) {
        this.siteConcept = siteConcept;
    }

    /**
     * @return サービス開始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate サービス開始日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return recipeHeadListRef
     */
    public InverseModelListRef<RecipeHead, SystemInfo> getRecipeHeadListRef() {
        return recipeHeadListRef;
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
        SystemInfo other = (SystemInfo) obj;
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
