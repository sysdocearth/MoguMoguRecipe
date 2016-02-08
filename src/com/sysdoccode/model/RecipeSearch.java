package com.sysdoccode.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

/**
 * レシピ検索のモデルクラスです。
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
@Model(schemaVersion = 1)
public class RecipeSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    /** 検索候補リスト */
    private String label;
    
    /** 検索結果テキストボックスに表示される値*/
    private String value;

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
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label セットする label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value セットする value
     */
    public void setValue(String value) {
        this.value = value;
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
        RecipeSearch other = (RecipeSearch) obj;
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
