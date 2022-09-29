package com.jaggaer.moviedb.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationRequest {

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    public AuthenticationRequest(@NotNull @Size(max = 255) String email, @NotNull @Size(max = 255) String password) {
      this.email = email;
      this.password = password;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String toString(){
      return this.email +":"+ this.password;
    }
}
/*
public class AuthenticationResponse {

    private String accessToken;

}
*/