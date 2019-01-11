package base;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：liuhai
 * 时间：2019/1/11:10:57
 * 邮箱：185587041@qq.com
 * 说明：管理所有的初始化操作,在BaseConfig中写入每个Module的全路径名，通过反射进行初始化操作
 */
public class ModuleInitManager {

    private List<BaseInitInterface> initlist = new ArrayList<>();

    private volatile static ModuleInitManager manager;

    private ModuleInitManager() {

    }

    public static ModuleInitManager getInstance() {
        if (manager == null) {
            synchronized (ModuleInitManager.class) {
                if (manager == null) {
                    manager = new ModuleInitManager();

                    return manager;
                }
            }
        }
        return manager;
    }


    /**
     * 通过反射读取每个初始化的类
     */
    private  void init(){
        String[] classes=BaseConfig.initClass;
        for (int i = 0; i <classes.length ; i++) {
            try {
            BaseInitInterface initInterface= (BaseInitInterface) Class.forName(classes[i]).newInstance();
            addModule(initInterface);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加Module
     * @param initInterface
     */
    private  void addModule(BaseInitInterface initInterface) {
        initlist.add(initInterface);
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void initModules(Context context) {
        init();
        for (BaseInitInterface module : initlist) {
            module.init(context);
        }
    }

    /**
     * 释放
     *
     */
    public void Trim(){
        for (BaseInitInterface module : initlist) {
            module.trim();
        }
    }


}
