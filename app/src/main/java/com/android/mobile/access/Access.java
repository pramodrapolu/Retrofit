package com.android.mobile.access;

import com.android.mobile.access.api.ArticleSearchApi;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

public class Access {
    private static final String TAG = Access.class.getSimpleName();

    private static final String API_URL = "http://api.nytimes.com";

    private static Access instance;
    private static final int TIMEOUT = 60;

    private static final RestAdapter.LogLevel LOGLEVEL = RestAdapter.LogLevel.BASIC;
    private OkClient okClient;

    private JacksonConverter converter;

    private ArticleSearchApi articleSearchApi;

    public static Access getInstance() {
        if (instance == null) {
            synchronized (Access.class) {
                // Double check
                if (instance == null) {
                    instance = new Access();
                }
            }
        }
        return (instance);
    }

    private Access() {
        // Make HTTP client
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setFollowRedirects(false);
        okClient = new OkClient(okHttpClient);

        // Make JSON converter
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        converter = new JacksonConverter(mapper);
    }

    public ArticleSearchApi getArticleSearchApi() {
        if (articleSearchApi != null) {
            return articleSearchApi;
        }

        // Build API
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setClient(okClient);
        builder.setEndpoint(API_URL);

        builder.setConverter(converter);
        builder.setLogLevel(LOGLEVEL);
        builder.setLog(new AndroidLog(TAG));
        RestAdapter adapter = builder.build();
        articleSearchApi = adapter.create(ArticleSearchApi.class);

        return(articleSearchApi);

    }
}
