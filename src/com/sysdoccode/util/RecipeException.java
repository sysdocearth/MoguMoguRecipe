/**
 * 
 */
package com.sysdoccode.util;

/**
 * @author Y.Takeuchi
 *
 */
public class RecipeException extends Exception{

    private static final long serialVersionUID = 1L;
    
    /** �G���[�N���X�� */
    private String className;
    
    /** �G���[���b�Z�[�W */
    private String message;
    
    /** ���[�U�[�\�[�X�R�[�h�̏ꏊ*/
    private String userSourceCode;
    
    /**
     * �R���X�g���N�^ 
     * @param className �G���[�����������N���X��
     * @param methodName �G���[�������������\�b�h��
     * @param message �G���[���b�Z�[�W
     */
    public RecipeException(Exception e, String method){
        setClassName(e.getClass().getName());
        setMessage(e.getMessage());
        java.lang.StackTraceElement[] stack = e.getStackTrace();
        
        String atUserSourceCode = "---";
        for(StackTraceElement data : stack){
            
            if(StringUtils.contains(data.toString(), method))
            {
                atUserSourceCode = data.toString();
                break;
            }            
        }
        setUserSourceCode("\tat " + atUserSourceCode);
    }
        /**
     * �Œ���̃G���[���o��
     */
    public String toString()
    {
        return (getClassName() + ": " + getMessage() + "\n" + getUserSourceCode());
    }
    
    
    /**
     * @return className
     */
    public String getClassName() {
        return className;
    }


    /**
     * @param className �Z�b�g���� className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }


    /**
     * @param message �Z�b�g���� message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * @return userSourceCode
     */
    public String getUserSourceCode() {
        return userSourceCode;
    }


    /**
     * @param userSourceCode �Z�b�g���� userSourceCode
     */
    public void setUserSourceCode(String userSourceCode) {
        this.userSourceCode = userSourceCode;
    }

}
