package bw.as;

import com.ihsanbal.logging.LoggingInterceptor;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.platform.Platform;

/**
 * Created by Administrator on 2017/9/22.
 */



public class HttpUtil {


    //网络请求OKHttp
    public static void sendOkHttpRequest(String address, Callback callback){
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(com.ihsanbal.logging.Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build()).build();
//        client.interceptors().add(new LoggingInterceptor());
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }





}
