package com.codesuttlle.EmployeeDetails.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;


@Data

public class APIResponse<T> {
//    @Pattern(regexp = "hh:mm:ss dd/mm/yyyy")
    @JsonFormat(pattern = "hh:mm:ss dd/MM/yyyy ")
    private LocalDateTime timeStamp;
    private T data;
    private APIError error;

    public APIResponse(){
        this.timeStamp=LocalDateTime.now();
    }

    public APIResponse(T data) {
        this();
        this.data=data;
    }
    public APIResponse(APIError error){
        this();
        this.error=error;
    }
}
