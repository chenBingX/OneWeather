package com.chenbing.oneweather.Data.Network;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> implements Serializable {
  private static final long serialVersionUID = -1736555174786656870L;
  protected int error_code;
  @SerializedName("reason")
  protected String reason;
  @SerializedName("result")
  private String result;

  public String getReason() {
    return reason;
  }

  public String getResult() {
    return result;
  }
}
