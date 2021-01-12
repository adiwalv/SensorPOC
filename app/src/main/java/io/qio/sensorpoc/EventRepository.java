package io.qio.sensorpoc;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class EventRepository {
    private static EventRepository instance;

    private EventService eventService;

    public static EventRepository getInstance() {
        if (instance == null) {
            instance = new EventRepository();
        }
        return instance;
    }

    public EventRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://convex.convex-dev.qiodev.qiotec-internal.com/feeds/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        eventService = retrofit.create(EventService.class);
    }

    public EventService getEventService() {
        return eventService;
    }
}
