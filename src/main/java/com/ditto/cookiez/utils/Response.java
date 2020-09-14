package com.ditto.cookiez.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Response {


    public static JSONObject createJSON(int httpStatus, Object data, String msg) {
        JSONObject result = new JSONObject();
        result.put("msg", msg);
        result.put("code", httpStatus);
        result.put("data", data);
        return result;
    }

    public static JSONObject addValToData(JSONObject jsonObject, Map<String, Object> map) {
        JSONObject data = jsonObject.getJSONObject("data");
        data.putAll(map);
        jsonObject.put("data", data);
        return jsonObject;
    }

    public static ResponseEntity<JSONObject> result(int httpStatus, Object data, String msg) {
        return ResponseEntity.status(httpStatus).body(createJSON(httpStatus, data, msg));
    }

    public static ResponseEntity<JSONObject> ok(String msg) {
        int httpStatus = HttpStatus.OK.value();
        return ResponseEntity.status(httpStatus).body(createJSON(httpStatus, "", msg));
    }

    public static ResponseEntity<JSONObject> ok(String msg, Object data) {
        int httpStatus = HttpStatus.OK.value();
        return ResponseEntity.status(httpStatus).body(createJSON(httpStatus, data, msg));
    }

    public static ResponseEntity<JSONObject> bad(String msg, Object data) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(httpStatus).body(createJSON(httpStatus, data, msg));
    }
    public static ResponseEntity<JSONObject> bad(String msg) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(httpStatus).body(createJSON(httpStatus, "", msg));
    }

    public static ResponseEntity<JSONObject> notFound() {
        int httpStatus = HttpStatus.NOT_FOUND.value();
        return ResponseEntity.status(httpStatus).body(createJSON(httpStatus, "", "Not Found"));
    }

    //    TODO: the function of logger is weak, query what? create what?
    public static ResponseEntity<JSONObject> succeedToQuery(Object data) {
        String msg = ResponseMsg.SUCCEED_TO_GET.v();

        return ok(msg,data);
    }

    public static ResponseEntity<JSONObject> succeedToCreate(Object data) {
        String msg = ResponseMsg.SUCCEED_TO_CREATE.v();

        return ok(msg,data);
    }

    public static ResponseEntity<JSONObject> succeedToUpdate(Object data) {
        String msg = ResponseMsg.SUCCEED_TO_UPDATE.v();
        return ok(msg,data);
    }

    public static ResponseEntity<JSONObject> succeedToDelete() {
        String msg = ResponseMsg.SUCCEED_TO_DELETE.v();
        return ok(msg);
    }

    public static ResponseEntity<JSONObject> failedToQuery() {
        String msg = ResponseMsg.FAILED_TO_GET.v();
        return bad(msg);
    }

    public static ResponseEntity<JSONObject> failedToCreate() {
        String msg = ResponseMsg.FAILED_TO_CREATE.v();

        return bad(msg);
    }

    public static ResponseEntity<JSONObject> failedToUpdate() {
        String msg = ResponseMsg.FAILED_TO_UPDATE.v();

        return bad(msg);
    }

    public static ResponseEntity<JSONObject> failedToDelete() {
        String msg = ResponseMsg.FAILED_TO_DELETE.v();
        return bad(msg);
    }


}
