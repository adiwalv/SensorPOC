package io.qio.sensorpoc;

import io.qio.sensorpoc.domain.Event;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventInterface {
    @POST("sse")
    Call<Void> postEvent(@Body Event event);
}
