package com.example.demo.exceptions;

public class Response {

    protected String name;
    protected String message;

    public Response(){}

    public Response(String name,String message){
        this.name=name;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
