package com.auth2.client;

import com.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "user-service")
public interface UserClient extends AccountApi {


}
