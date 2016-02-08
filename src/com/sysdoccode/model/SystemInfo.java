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
 * �V�X�e�����̃N���X
 * 
 * @author Y.Takeuchi
 *�@@version 1.0.0
 */
@Model(schemaVersion = 1)
public class SystemInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    /** �I�[�i�[���O */
    private String ownerName;
    
    /** �A���惁�[���A�h���X*/
    private String mailAddress;
    
    /** �T�C�g�R���Z�v�g */
    private String siteConcept;
    
    /** �T�[�r�X�J�n�� */
    @Attribute(listener = CreationDate.class)
    private Date startDate;
    
    /** RecipeHeader�ւ̑o����1�Α��֘A */
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
     * @return �I�[�i�[���O
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName �I�[�i�[���O
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return �A���惁�[���A�h���X
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param mailAddress �A���惁�[���A�h���X
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * @return �T�C�g�R���Z�v�g
     */
    public String getSiteConcept() {
        return siteConcept;
    }

    /**
     * @param siteConcept �T�C�g�R���Z�v�g
     */
    public void setSiteConcept(String siteConcept) {
        this.siteConcept = siteConcept;
    }

    /**
     * @return �T�[�r�X�J�n��
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate �T�[�r�X�J�n��
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
