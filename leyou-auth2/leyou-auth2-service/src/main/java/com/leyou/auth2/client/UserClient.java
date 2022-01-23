package com.leyou.auth2.client;

import com.leyou.user.api.UserApi;
import com.leyou.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA
 *
 * @author: Lu Yujie
 * on2018/11/2
 * 19:43
 */

@FeignClient(value = "user-service")
public interface UserClient extends UserApi {


}
