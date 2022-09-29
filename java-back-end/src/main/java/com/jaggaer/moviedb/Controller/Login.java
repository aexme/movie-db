package com.jaggaer.moviedb.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaggaer.moviedb.Configuration.JwtUtils;
import com.jaggaer.moviedb.model.AuthenticationRequest;

@RestController
@RequestMapping("/v1")
public class Login {
   
    private final JwtUtils jwtUtils;

    Logger logger = LoggerFactory.getLogger(Login.class);    
    
    
    public Login(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    
    
    @RequestMapping(
      value="/signin", 
      method=RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody Map<String, String> signin(@RequestBody AuthenticationRequest user) {
      
        logger.info("Token requested for user: '{}'", user.toString());

        String token = jwtUtils.generateTokenFromUsername(user.getEmail());

        logger.info("Token generated : '{}'", token);

        final Map<String, String> mapA = new HashMap();
        mapA.put("response", token);
        return mapA;
    }
    
}