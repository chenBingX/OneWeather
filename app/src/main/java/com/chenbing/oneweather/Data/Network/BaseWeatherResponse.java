package com.chenbing.oneweather.Data.Network;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class BaseWeatherResponse<T> implements Serializable {
  private static final long serialVersionUID = -1736555174786656870L;
  @SerializedName("error_code")
  private int error_code;
  @SerializedName("reason")
  private String reason;
  @SerializedName("result")
  private T result;

  public int getError_code() { return error_code; }

  public String getReason() { return reason; }

  public T getResult() {
    return result;
  }

  public void setError_code(int error_code) {
    this.error_code = error_code;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
