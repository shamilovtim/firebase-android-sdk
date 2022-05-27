/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * Modify at your own risk.
 */

package com.google.api.services.firebaseapptesters.v1alpha.model;

/**
 * Message that represents an arbitrary HTTP body. It should only be used for payload formats that
 * can't be represented as JSON, such as raw binary or an HTML page. This message can be used both
 * in streaming and non-streaming API methods in the request as well as the response. It can be used
 * as a top-level request field, which is convenient if one wants to extract parameters from either
 * the URL or HTTP template into the request fields and also want access to the raw HTTP body.
 * Example: message GetResourceRequest { // A unique request id. string request_id = 1; // The raw
 * HTTP body is bound to this field. google.api.HttpBody http_body = 2; } service ResourceService {
 * rpc GetResource(GetResourceRequest) returns (google.api.HttpBody); rpc
 * UpdateResource(google.api.HttpBody) returns (google.protobuf.Empty); } Example with streaming
 * methods: service CaldavService { rpc GetCalendar(stream google.api.HttpBody) returns (stream
 * google.api.HttpBody); rpc UpdateCalendar(stream google.api.HttpBody) returns (stream
 * google.api.HttpBody); } Use of this type only changes how the request and response bodies are
 * handled, all other features will continue to work unchanged.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the Firebase App Testers API. For a detailed explanation
 * see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class GoogleApiHttpBody extends com.google.api.client.json.GenericJson {

  /**
   * The HTTP Content-Type header value specifying the content type of the body.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String contentType;

  /**
   * The HTTP request/response body as raw binary.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String data;

  /**
   * Application specific response metadata. Must be set in the first response for streaming APIs.
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.util.Map<String, java.lang.Object>> extensions;

  /**
   * The HTTP Content-Type header value specifying the content type of the body.
   * @return value or {@code null} for none
   */
  public java.lang.String getContentType() {
    return contentType;
  }

  /**
   * The HTTP Content-Type header value specifying the content type of the body.
   * @param contentType contentType or {@code null} for none
   */
  public GoogleApiHttpBody setContentType(java.lang.String contentType) {
    this.contentType = contentType;
    return this;
  }

  /**
   * The HTTP request/response body as raw binary.
   * @see #decodeData()
   * @return value or {@code null} for none
   */
  public java.lang.String getData() {
    return data;
  }

  /**
   * The HTTP request/response body as raw binary.
   * @see #getData()
   * @return Base64 decoded value or {@code null} for none
   *
   * @since 1.14
   */
  public byte[] decodeData() {
    return com.google.api.client.util.Base64.decodeBase64(data);
  }

  /**
   * The HTTP request/response body as raw binary.
   * @see #encodeData()
   * @param data data or {@code null} for none
   */
  public GoogleApiHttpBody setData(java.lang.String data) {
    this.data = data;
    return this;
  }

  /**
   * The HTTP request/response body as raw binary.
   * @see #setData()
   *
   * <p>
   * The value is encoded Base64 or {@code null} for none.
   * </p>
   *
   * @since 1.14
   */
  public GoogleApiHttpBody encodeData(byte[] data) {
    this.data = com.google.api.client.util.Base64.encodeBase64URLSafeString(data);
    return this;
  }

  /**
   * Application specific response metadata. Must be set in the first response for streaming APIs.
   * @return value or {@code null} for none
   */
  public java.util.List<java.util.Map<String, java.lang.Object>> getExtensions() {
    return extensions;
  }

  /**
   * Application specific response metadata. Must be set in the first response for streaming APIs.
   * @param extensions extensions or {@code null} for none
   */
  public GoogleApiHttpBody setExtensions(java.util.List<java.util.Map<String, java.lang.Object>> extensions) {
    this.extensions = extensions;
    return this;
  }

  @Override
  public GoogleApiHttpBody set(String fieldName, Object value) {
    return (GoogleApiHttpBody) super.set(fieldName, value);
  }

  @Override
  public GoogleApiHttpBody clone() {
    return (GoogleApiHttpBody) super.clone();
  }

}
