package utilities.apiUtils;

import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;


public class RestUtil {

    public static Response getByJson(String element, String url, String CONTENT_TYPE) {
        Response response = RestAssured.given()
                .header("Content-Type", CONTENT_TYPE)
                .body(element)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .get(url);
        return response;
    }

    public static Response postByJson(String element, String url, Map header) {

        Response response = RestAssured.given()
                .urlEncodingEnabled(false)
                .headers(header)
                .body(element)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .post(url);
        return response;
    }

    public static Response postByFile(String url, Map header, java.io.File file, String type) {

        Response response = RestAssured.given()
                .multiPart("image", file, type)
                .headers(header)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .post(url);
        return response;
    }

    public static Response postByUrlLogout(String url, Map header, String body) {

        Response response = RestAssured
                .given()
                .urlEncodingEnabled(false)
                .headers(header)
                .body(body)
                .log()
                .all()
                .when()
                .relaxedHTTPSValidation()
                .post(url);
        return response;
    }

    public static Response getByUrl(String url, Map header) {
        Response response = RestAssured.given()
                .urlEncodingEnabled(false)
                .headers(header)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .get(url);
        return response;
    }

    public static Response postByUrl(String url) {
        Response response = RestAssured.
                given()
                .urlEncodingEnabled(false)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .post(url);
        return response;
    }

    public static Response postByUrl(String url, Map header, String body) {
        Response response = RestAssured.
                given()
                .urlEncodingEnabled(false)
                .headers(header)
                .contentType("application/json")
                .log()
                .all()
                .relaxedHTTPSValidation()
                .body(body)
                .post(url);
        return response;
    }

    public static Response postByUrl(String url, Map header) {
        Response response = RestAssured.
                given()
                .urlEncodingEnabled(false)
                .headers(header)
                .contentType("application/x-www-form-urlencoded")
                .log()
                .all()
                .relaxedHTTPSValidation()
                .post(url);
        return response;
    }

    public static Response putByUrl(String url, Map header, String body) {
        Response response = RestAssured.
                given()
                .urlEncodingEnabled(false)
                .headers(header)
                .contentType("application/json")
                .log()
                .all()
                .relaxedHTTPSValidation()
                .body(body)
                .put(url);
        return response;
    }

    public static Response get(Map<String, String> params, String path, String CONTENT_TYPE) {

        Response response = RestAssured
                .given()
                .urlEncodingEnabled(false)
                .header("Content-Type", CONTENT_TYPE)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .formParams(params)
                .get(path);
        return response;
    }

    public static Response get(Map<String, String> params, Map<String, String> headers, String path) {

        Response response = RestAssured
                .given()
                .urlEncodingEnabled(false)
                .headers(headers)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .formParams(params)
                .get(path);
        return response;
    }


    public static Response getByUrl(String url, String CONTENT_TYPE) {
        Response response = RestAssured.
                given()
                .urlEncodingEnabled(false)
                .header("Content-Type", CONTENT_TYPE)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .get(url);
        return response;
    }

    public static Response getByUrl(String url) {
        Response response = RestAssured.
                given()
                .urlEncodingEnabled(false)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .get(url);
        return response;
    }

    public static String getResponseParam(Response response, String param) throws JsonException {
        String responseReceived = response.body().asString();
        System.out.println("responseReceived: " + responseReceived);
        JSONObject jsonObject = new JSONObject(responseReceived);
        return getResponseParam(jsonObject, param);
    }

    public static String getResponseParam(JSONObject jsonObject, String param) throws JsonException {
        System.out.println("jsonObject: " + jsonObject);
        String resparam = jsonObject.get(param).toString();
        return resparam;
    }

    public static Response postByPut(String element, String url, String CONTENT_TYPE) {

        Response response = RestAssured.given()
                .header("Content-Type", CONTENT_TYPE)
                .body(element)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .put(url);
        return response;
    }

    public static Response post(Map<String, String> data, String path,
                                String CONTENT_TYPE) {
        Response response = RestAssured.given()
                .header("Content-Type", CONTENT_TYPE)
                .body(data)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .post(path);
        return response;

    }

    public static JSONArray getResponsejson(Response response, String param) {
        String responseReceived = response.body().asString();
        System.out.println("responseReceived: " + responseReceived);
        JSONObject jsonObject = new JSONObject(responseReceived);
        return getResponseJson(jsonObject, param);
    }

    private static JSONArray getResponseJson(JSONObject jsonObject, String param) {

        System.out.println("jsonObject: " + jsonObject);
        JSONArray resparam = jsonObject.getJSONArray(param);
        return resparam;

    }

    public static Response postForToken(Map<String, String> data, String path, String contentType) {
        Response response = RestAssured.given()
                .header("Content-Type", contentType)
                .body(data)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .post(path);
        return response;
    }


    public static Response delete(String url, Map header) {
        Response response = RestAssured.given()
                .headers(header)
                .log()
                .all()
                .relaxedHTTPSValidation()
                .when()
                .delete(url);
        return response;
    }

    public RequestSpecification getRequestSpecification() {
        return RestAssured.given().contentType(ContentType.JSON);
    }
}



