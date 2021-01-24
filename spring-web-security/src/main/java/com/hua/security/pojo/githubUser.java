package com.hua.security.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class githubUser {
    private String uuid;
    private String username;
    private String avatarUrl;
    private String accessToken;
    private String tokenType;

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return "githubUser{" +
                "uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
