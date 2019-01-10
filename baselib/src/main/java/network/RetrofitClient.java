package network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.Settings.System.DATE_FORMAT;

/**
 * retrofit初始化
 */
  class RetrofitClient {

    private volatile static RetrofitClient retrofit;
    //
    private static ConcurrentHashMap<String, Retrofit> map = new ConcurrentHashMap<>();




    private  RetrofitClient() {

    }

    protected static   RetrofitClient getRetrofitClient() {
        if (retrofit == null) {
            synchronized (RetrofitClient.class) {
                if (retrofit == null) {

                    retrofit = new RetrofitClient();

                    return retrofit;
                }
            }
        }
        return retrofit;
    }


    /**
     * @param anthoerHost okhttp支持设置不同的HOST
     * @return
     */
    protected  Retrofit getRetrofitWithDifferentHost(String anthoerHost) {
        if (map.get(anthoerHost) == null) {
            Retrofit retrofit = CreateRetrofit(anthoerHost);
            map.put(anthoerHost, retrofit);
            return retrofit;
        }
        return map.get(anthoerHost);
    }

    protected Retrofit getDefaultClient(){
        return  getRetrofitWithDifferentHost(NetWorkConfig.BASEURL);
    }

    private  Retrofit CreateRetrofit(String BaseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return null;
                    }
                })
                //  .addInterceptor(new HttpInterceptor(new HashMap<String, String>()))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT).create();
        return new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.from(getSelfThreadPool())))
                .client(okHttpClient)
                .build();
    }


    private static  ThreadPoolExecutor poolExecutor;

    /**
     * 综合cache和FIX的优缺点 防止RXJAVA线程池溢出OOM的错误
     * @return
     */
   private ThreadPoolExecutor getSelfThreadPool(){
       //自定义线程池，防止RXJAVA开启很多线程后出现OM的错误
       if(poolExecutor==null) {
           poolExecutor = new ThreadPoolExecutor(0, 64,
                   0L, TimeUnit.MILLISECONDS,
                   new LinkedBlockingQueue<Runnable>());
       }


    return   poolExecutor;
    }

}
