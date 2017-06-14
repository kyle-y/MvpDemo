package com.example.administrator.mvpdemo.m.model;

import com.example.administrator.mvpdemo.m.service.DoubanService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Administrator on 2017/6/12.
 */

@Module(includes = {AppModule.class, ClientModule.class})
public class ApiModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    public DoubanService provideDoubanService(Retrofit.Builder builder, OkHttpClient client){
        return creatRetrofit(builder, DoubanService.API_DOUBAN, client).create(DoubanService.class);
    }

    private Retrofit creatRetrofit(Retrofit.Builder builder, String baseUrl, OkHttpClient client){
        builder.client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create());
        return builder.build();
    }
}
