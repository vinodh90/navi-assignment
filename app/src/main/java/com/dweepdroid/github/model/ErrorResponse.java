package com.dweepdroid.github.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    @SerializedName("message")
    String message;

    @SerializedName("documentation_url")
    String documentationUrl;

    public ErrorResponse(){

    }

    public ErrorResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDocumentationUrl() {
        return documentationUrl;
    }


}
