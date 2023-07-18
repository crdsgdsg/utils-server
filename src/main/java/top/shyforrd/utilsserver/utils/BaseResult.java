package top.shyforrd.utilsserver.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 14645
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {
    private int code;
    private String message;
    private Boolean success;
    private T data;

    public static <T> BaseResult<T> success(int code, String msg, T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setMessage(msg);
        baseResult.setData(data);
        baseResult.setSuccess(true);
        return baseResult;
    }

    public static <T> BaseResult<T> success() {
        return success(200, "成功", null);
    }

    public static <T> BaseResult<T> success(T data) {
        return success(200, "成功", data);
    }

    public static <T> BaseResult<T> success(String msg) {
        return success(200, msg, null);
    }

    public static <T> BaseResult<T> success(String msg, T data) {
        return success(200, msg, data);
    }

    public static <T> BaseResult<T> fail(int code, String msg, T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setMessage(msg);
        baseResult.setData(data);
        baseResult.setSuccess(false);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(int code) {
        return success(code, "失败", null);
    }

    public static <T> BaseResult<T> fail(String msg) {
        return success(500, msg, null);
    }

    public static <T> BaseResult<T> fail(int code, String msg) {
        return success(code, msg, null);
    }

    public static <T> BaseResult<T> fail() {
        return success(500, "失败", null);
    }
}
