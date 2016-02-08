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
    
    /** エラークラス名 */
    private String className;
    
    /** エラーメッセージ */
    private String message;
    
    /** ユーザーソースコードの場所*/
    private String userSourceCode;
    
    /**
     * コンストラクタ 
     * @param className エラーが発生したクラス名
     * @param methodName エラーが発生したメソッド名
     * @param message エラーメッセージ
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
     * 最低限のエラー情報出力
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
     * @param className セットする className
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
     * @param message セットする message
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
     * @param userSourceCode セットする userSourceCode
     */
    public void setUserSourceCode(String userSourceCode) {
        this.userSourceCode = userSourceCode;
    }

}
