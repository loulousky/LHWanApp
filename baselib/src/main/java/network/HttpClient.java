package network;

import com.example.liuhai.myapplication.GlideApp;

import java.util.Observable;

import retrofit2.Retrofit;

/**
 * 暴露给外部使用的网络请求框架
 */
public class HttpClient {
    private static volatile  HttpClient client;
    private RetrofitClient retrofitClient=RetrofitClient.getRetrofitClient();
    public  static final HttpClient getInstace(){
        if(client==null){
            synchronized(HttpClient.class){
                if(client==null){
                    client=new HttpClient();
                    return client;
                }
            }
        }
        return client;
    }


    /**
     * 拿到默认的数据接口
     * @param t CLASS类型
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> t) throws Exception {
        if(!t.isInterface()){
            throw new Exception("必须是接口"+HttpClient.class.getClass().getName());
        }
        return  retrofitClient.getDefaultClient().create(t);

    }

    /**
     * 拿到指定HOST的数据接口
     * @param t
     * @param host
     * @param <T>
     * @return
     */
  public <T> T create(Class<T> t,String host) throws Exception {

      if(!t.isInterface()){
          throw new Exception("必须是接口"+HttpClient.class.getClass().getName());
      }



        return retrofitClient.getRetrofitWithDifferentHost(host).create(t);

  }







}
