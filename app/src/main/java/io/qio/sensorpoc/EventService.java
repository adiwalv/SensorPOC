package io.qio.sensorpoc;

import java.util.Map;

import io.qio.sensorpoc.domain.Event;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EventService {
    @POST("sse")
    Call<Void> postEvent(@Body Event event);
}
