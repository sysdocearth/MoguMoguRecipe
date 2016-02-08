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
 * ���V�s�w�b�_�[���̃N���X
 * 
 * @author Y.Takeuchi
 * @version 1.0.0
 */
@Model(schemaVersion = 1)
public class RecipeHead implements Serializable {

    private static final long serialVersionUID = 1L;
    
    ///////////////////////
    // ���V�s�J�e�S��
    ///////////////////////
    public enum CATEGORY {
        MEAT            (0      ,"��؁F�i�����j�̂�����"               ,"/img/meat.png"  )
        , FISH          (1      ,"��؁F�i�����j�̂�����"               ,"/img/fish.png")
        , EGGSTOFU      (2      ,"��؁F�i���E�����j�̂�����"            ,"/img/egg.png")
        , MAINFSOUP     (3      ,"��H�E�`���́F���āE�p�X�^�E�X�[�v �Ȃ�"    ,"/img/soop.png")
        , SIDEDISH      (4      ,"���؁F��؁E���̂��̂�����"            ,"/img/carrots.png")
        , DESSERT       (5      ,"����̈�i�F�f�U�[�g�E���َq �Ȃ�"       ,"/img/cake.png")
        ;
        
        private int intValue;
        private String strValue;
        private String imgFileName;
        
        CATEGORY(final int kind, final String desc, final String fileName){
            intValue        = kind ;
            strValue        = desc ;
            imgFileName     = fileName;
        }
        
        // enum�萔���琮���֕ϊ�
        public int getIntValue() {
            return intValue;
        }
        
        // enum�萔���當����֕ϊ�
        public String getStrValue() {
            return strValue;
        }
        
        public String getImgFileName() {
            return imgFileName;
        }
        
        // ��������enum�萔�֕ϊ�
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
    
    /** ���V�s�^�C�g�� */
    private String title;
    
    /** ���V�s�J�e�S���[ */
    private CATEGORY category;
    
    /** �Љ�R�����g�@*/
    private String introduction;
    
    /** �����������x�� (1�`5)*/
    private int mogumoguLevel = 1;
    
    /** �����o�^�i�܂��������Ă��Ȃ��ꍇ:"y"�j*/
    private String noRatings;
    
    /** �쐬���ԁi���j */
    private String minute;
  
    /** �l�O */
    private String servings;
    
    /** �y���l�[��*/
    private String author;
    
    /** �ҏW�p�p�X���[�h*/
    @Attribute(cipher=true)
    private String password;
    
    /** ��ԃ��V�s(��ԃ��V�s�̏ꍇ�F"y") */
    private String classicRecipe;
    
    /** ���V�s�쐬�� */
    @Attribute(listener = CreationDate.class)
    private Date createDate;
    
    /** ���V�s�X�V�� */
    @Attribute(listener = ModificationDate.class)
    private Date updateDate;
    
    /** SystemInfo �ւ̑���1�֘A�̊O���L�[  */
    private ModelRef<SystemInfo> systemInfoRef = new ModelRef<SystemInfo>(SystemInfo.class);
    
    /** RecipeBody�ւ�1��1�̊֘A  */
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
     * @return ���V�s�^�C�g��
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title ���V�s�^�C�g��
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return ���V�s�J�e�S��
     */
    public CATEGORY getCategory() {
        return category;
    }

    /**
     * @param category ���V�s�J�e�S��
     */
    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    /**
     * @return ���V�s�Љ
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction ���V�s�Љ
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return �����������x���i1�`5�j
     */
    public int getMogumoguLevel() {
        return mogumoguLevel;
    }

    /**
     * @param mogumoguLevel �����������x���i1�`5�j
     */
    public void setMogumoguLevel(int mogumoguLevel) {
        this.mogumoguLevel = mogumoguLevel;
    }

    /**
     * @return �����o�^�i�܂��������Ă��Ȃ��ꍇ:y�j
     */
    public String getNoRatings() {
        return noRatings;
    }

    /**
     * @param �����o�^�i�܂��������Ă��Ȃ��ꍇ:y�j 
     */
    public void setNoRatings(String noRatings) {
        this.noRatings = noRatings;
    }

    /**
     * @return �쐬���ԁi���j 
     */
    public String getMinute() {
        return minute;
    }

    /**
     * @param �쐬���ԁi���j 
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * @return �l�O
     */
    public String getServings() {
        return servings;
    }

    /**
     * @param servings �l�O
     */
    public void setServings(String servings) {
        this.servings = servings;
    }

    /**
     * @return �y���l�[��
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author �y���l�[��
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return �ҏW�p�p�X���[�h
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password �ҏW�p�p�X���[�h
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
     * @param classicRecipe �Z�b�g���� classicRecipe
     */
    public void setClassicRecipe(String classicRecipe) {
        this.classicRecipe = classicRecipe;
    }

    /**
     * @return ���V�s�쐬��
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate ���V�s�쐬��
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return ���V�s�X�V��
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate ���V�s�X�V��
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return systemInfoRef �Q�Ɛ�ł��� SystemInfo���̎Q��
     */
    public ModelRef<SystemInfo> getSystemInfoRef() {
        return systemInfoRef;
    }

    /**
     * @return recipeBodyRef�@�Q�Ɛ�ł���RecipeBody���̎擾
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
