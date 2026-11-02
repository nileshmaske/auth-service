package com.shreejewellers.auth.api;
public record LoginResponse(String accessToken,String tokenType,long expiresIn){}
