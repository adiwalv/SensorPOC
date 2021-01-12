package io.qio.sensorpoc;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class EventRestService {
    private static EventRestService instance;

    private EventInterface eventInterface;

    public static EventRestService getInstance() {
        if (instance == null) {
            instance = new EventRestService();
        }
        return instance;
    }

    public EventRestService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://convex.convex-dev.qiodev.qiotec-internal.com/feeds/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        eventInterface = retrofit.create(EventInterface.class);
    }

    public EventInterface getEventInterface() {
        return eventInterface;
    }
}
