package util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.liuhai.baselib.BuildConfig;

/**
 * 作者：liuhai
 * 时间：2019/1/10:14:18
 * 邮箱：185587041@qq.com
 * 说明：SP存储工具类
 */
public class SpUtils {

    private static volatile SharedPreferences sharedPreferences;
    private static  SharedPreferences.Editor editor;

    public static SharedPreferences getInstance(Context context){
        if(sharedPreferences==null){

            synchronized (SpUtils.class){
                if(sharedPreferences==null){
                    sharedPreferences=context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    return sharedPreferences;
                }
            }
        }

        return sharedPreferences;
    }

    public String getString(String name){
       return sharedPreferences.getString(name,null);
    }

    public String getString(String name,String def){
        return sharedPreferences.getString(name,def);
    }

    public int getInt(String name){

        return sharedPreferences.getInt(name,-1);
    }


    public int getInt(String name,int def){

        return sharedPreferences.getInt(name,def);
    }


    public float getFloat(String name){
        return sharedPreferences.getFloat(name,-1);
    }


    public float getFloat(String name,float def){

        return sharedPreferences.getFloat(name,def);
    }


    public long getLong(String name){
        return sharedPreferences.getLong(name,-1);
    }


    public long getLong(String name,long def){
        return sharedPreferences.getLong(name,def);
    }


    public boolean getBoolean(String name){
        return  sharedPreferences.getBoolean(name,false);
    }

    public boolean getBoolean(String name,boolean def){

        return sharedPreferences.getBoolean(name,def);
    }







    /**
     * 存String
     * @param name
     * @param value
     */
    public void putStringValue(String name,String value){
      editor.putString(name,value);
      editor.commit();
    }


    /**
     * 存Boolean
     * @param name
     * @param value
     */
    public void putBooleanValue(String name,boolean value){
        editor.putBoolean(name,value);
        editor.commit();
    }


    /**
     * 存float
     * @param name
     * @param value
     */
    public void putFloatalue(String name,float value){
        editor.putFloat(name,value);
        editor.commit();
    }


    /**
     * 存int
     * @param name
     * @param value
     */
    public void putIntValue(String name,int value){
        editor.putInt(name,value);
        editor.commit();
    }


    /**
     * 存long
     * @param name
     * @param value
     */
    public void putLongValue(String name,long value){
     editor.putLong(name,value);
        editor.commit();
    }


    /**
     * 清除所有数据
     */
    public void Clear(){
        editor.clear();
    }


}
