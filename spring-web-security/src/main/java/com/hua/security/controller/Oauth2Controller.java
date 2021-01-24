package com.hua.security.controller;


import com.alibaba.fastjson.JSONObject;
import com.hua.security.service.UserService;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/oauth")
public class Oauth2Controller {
    @Autowired
    UserService userService ;
    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public String login(AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse<AuthUser> response=authRequest.login(callback);

        System.out.println(JSONObject.toJSONString(response));
        if(response.ok()){
            userService.loadUserByUsername(response.getData().getUsername());
            return "index";
        }else{
            return "error/404";
        }
    }

    private AuthRequest getAuthRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("2c66aec4b5664ab037b7")
                .clientSecret("ff7b7ad267a4745be67788a02fc3d4df000cc05e")
                .redirectUri("http://localhost:8080/oauth/callback")
                .build());
    }
}
