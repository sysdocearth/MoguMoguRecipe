package com.sysdoccode.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

/**
 * [Ajax]�@Top��� Json�`���ŕԂ������̃��f���N���X�ł��B
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
    
    /** Google���O�C���A�J�E���g*/
    private String accountId;
    
    /** ���V�s�S���� */
    private long allCount = 0; 
    
    /** �����̂��������V�s���� */
    private long meatCount = 0;
    
    /** �����Ȃ̂��������V�s���� */
    private long fishCount = 0;
    
    /** ���Ɠ����̂��������V�s����  */
    private long eggTofuCount = 0;
    
    /** ��H�E�`�� ���V�s���� */
    private long mainSoupCount = 0;
    
    /** ���؃��V�s���� */
    private long sideDishCount = 0;
    
    /** �f�U�[�g���V�s���� */
    private long dessertCount = 0;
    
    /** �T�C�g�R���Z�v�g�@*/
    private String siteConcept;
    
    /** ���[�U�[���O�C����� (0:Google�����O�C��, 1:Google���O�C��)*/
    private  int googleLogin = 0;
 
    /** ���O�C��URL*/
    private String loginURL;
    
    /** ���O�A�E�gURL */
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
     * @param accountId �Z�b�g���� accountId
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
     * @param allCount �Z�b�g���� allCount
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
     * @param meatCount �Z�b�g���� meatCount
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
     * @param fishCount �Z�b�g���� fishCount
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
     * @param eggTofuCount �Z�b�g���� eggTofuCount
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
     * @param mainSoupCount �Z�b�g���� mainSoupCount
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
     * @param sideDishCount �Z�b�g���� sideDishCount
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
     * @param dessertCount �Z�b�g���� dessertCount
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
     * @param siteConcept �Z�b�g���� siteConcept
     */
    public void setSiteConcept(String siteConcept) {
        this.siteConcept = siteConcept;
    }

    /**
     * @return Google���O�C���F1, Google�����O�C���F0
     */
    public int getGoogleLogin() {
        return googleLogin;
    }

    /**
     * @param Google���O�C���F1, Google�����O�C���F0
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
     * @param loginURL �Z�b�g���� loginURL
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
     * @param logoutURL �Z�b�g���� logoutURL
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
