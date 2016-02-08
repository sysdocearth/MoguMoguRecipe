package com.sysdoccode.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

/**
 * [Ajax]　Top画面 Json形式で返しい情報のモデルクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
@Model(schemaVersion = 1)
public class ActivityInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    @Json(ignore = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** Googleログインアカウント*/
    private String accountId;
    
    /** レシピ全件数 */
    private long allCount = 0; 
    
    /** お肉のおかずレシピ件数 */
    private long meatCount = 0;
    
    /** さかなのおかずレシピ件数 */
    private long fishCount = 0;
    
    /** 卵と豆腐のおかずレシピ件数  */
    private long eggTofuCount = 0;
    
    /** 主食・汁物 レシピ件数 */
    private long mainSoupCount = 0;
    
    /** 副菜レシピ件数 */
    private long sideDishCount = 0;
    
    /** デザートレシピ件数 */
    private long dessertCount = 0;
    
    /** サイトコンセプト　*/
    private String siteConcept;
    
    /** ユーザーログイン情報 (0:Google未ログイン, 1:Googleログイン)*/
    private  int googleLogin = 0;
 
    /** ログインURL*/
    private String loginURL;
    
    /** ログアウトURL */
    private String logoutURL;

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
     * @return accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId セットする accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return allCount
     */
    public long getAllCount() {
        return allCount;
    }

    /**
     * @param allCount セットする allCount
     */
    public void setAllCount(long allCount) {
        this.allCount = allCount;
    }

    /**
     * @return meatCount
     */
    public long getMeatCount() {
        return meatCount;
    }

    /**
     * @param meatCount セットする meatCount
     */
    public void setMeatCount(long meatCount) {
        this.meatCount = meatCount;
    }

    /**
     * @return fishCount
     */
    public long getFishCount() {
        return fishCount;
    }

    /**
     * @param fishCount セットする fishCount
     */
    public void setFishCount(long fishCount) {
        this.fishCount = fishCount;
    }

    /**
     * @return eggTofuCount
     */
    public long getEggTofuCount() {
        return eggTofuCount;
    }

    /**
     * @param eggTofuCount セットする eggTofuCount
     */
    public void setEggTofuCount(long eggTofuCount) {
        this.eggTofuCount = eggTofuCount;
    }

    /**
     * @return mainSoupCount
     */
    public long getMainSoupCount() {
        return mainSoupCount;
    }

    /**
     * @param mainSoupCount セットする mainSoupCount
     */
    public void setMainSoupCount(long mainSoupCount) {
        this.mainSoupCount = mainSoupCount;
    }

    /**
     * @return sideDishCount
     */
    public long getSideDishCount() {
        return sideDishCount;
    }

    /**
     * @param sideDishCount セットする sideDishCount
     */
    public void setSideDishCount(long sideDishCount) {
        this.sideDishCount = sideDishCount;
    }

    /**
     * @return dessertCount
     */
    public long getDessertCount() {
        return dessertCount;
    }

    /**
     * @param dessertCount セットする dessertCount
     */
    public void setDessertCount(long dessertCount) {
        this.dessertCount = dessertCount;
    }

    /**
     * @return siteConcept
     */
    public String getSiteConcept() {
        return siteConcept;
    }

    /**
     * @param siteConcept セットする siteConcept
     */
    public void setSiteConcept(String siteConcept) {
        this.siteConcept = siteConcept;
    }

    /**
     * @return Googleログイン：1, Google未ログイン：0
     */
    public int getGoogleLogin() {
        return googleLogin;
    }

    /**
     * @param Googleログイン：1, Google未ログイン：0
     */
    public void setGoogleLogin(int googleLogin) {
        this.googleLogin = googleLogin;
    }

    /**
     * @return loginURL
     */
    public String getLoginURL() {
        return loginURL;
    }

    /**
     * @param loginURL セットする loginURL
     */
    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    /**
     * @return logoutURL
     */
    public String getLogoutURL() {
        return logoutURL;
    }

    /**
     * @param logoutURL セットする logoutURL
     */
    public void setLogoutURL(String logoutURL) {
        this.logoutURL = logoutURL;
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
        ActivityInfo other = (ActivityInfo) obj;
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
