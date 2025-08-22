# SampleControllerApi

All URIs are relative to *http://localhost:8080/myservice-service*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSample**](SampleControllerApi.md#createSample) | **POST** /sample |  |
| [**deleteSample**](SampleControllerApi.md#deleteSample) | **DELETE** /sample/{id} |  |
| [**getAllSamples**](SampleControllerApi.md#getAllSamples) | **GET** /sample |  |
| [**getSampleById**](SampleControllerApi.md#getSampleById) | **GET** /sample/{id} |  |
| [**updateSample**](SampleControllerApi.md#updateSample) | **PUT** /sample/{id} |  |



## createSample

> ModelApiResponse createSample(xTenantID, xUserID, authToken, sample)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SampleControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/myservice-service");

        SampleControllerApi apiInstance = new SampleControllerApi(defaultClient);
        String xTenantID = "xTenantID_example"; // String | 
        String xUserID = "xUserID_example"; // String | 
        String authToken = "authToken_example"; // String | 
        Sample sample = new Sample(); // Sample | 
        try {
            ModelApiResponse result = apiInstance.createSample(xTenantID, xUserID, authToken, sample);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SampleControllerApi#createSample");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **xTenantID** | **String**|  | |
| **xUserID** | **String**|  | |
| **authToken** | **String**|  | |
| **sample** | [**Sample**](Sample.md)|  | |

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## deleteSample

> ModelApiResponse deleteSample(id, xTenantID, xUserID, authToken)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SampleControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/myservice-service");

        SampleControllerApi apiInstance = new SampleControllerApi(defaultClient);
        Long id = 56L; // Long | 
        String xTenantID = "xTenantID_example"; // String | 
        String xUserID = "xUserID_example"; // String | 
        String authToken = "authToken_example"; // String | 
        try {
            ModelApiResponse result = apiInstance.deleteSample(id, xTenantID, xUserID, authToken);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SampleControllerApi#deleteSample");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **xTenantID** | **String**|  | |
| **xUserID** | **String**|  | |
| **authToken** | **String**|  | |

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## getAllSamples

> ModelApiResponse getAllSamples(xTenantID, xUserID, authToken)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SampleControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/myservice-service");

        SampleControllerApi apiInstance = new SampleControllerApi(defaultClient);
        String xTenantID = "xTenantID_example"; // String | 
        String xUserID = "xUserID_example"; // String | 
        String authToken = "authToken_example"; // String | 
        try {
            ModelApiResponse result = apiInstance.getAllSamples(xTenantID, xUserID, authToken);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SampleControllerApi#getAllSamples");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **xTenantID** | **String**|  | |
| **xUserID** | **String**|  | |
| **authToken** | **String**|  | |

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## getSampleById

> ModelApiResponse getSampleById(id, xTenantID, xUserID, authToken)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SampleControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/myservice-service");

        SampleControllerApi apiInstance = new SampleControllerApi(defaultClient);
        Long id = 56L; // Long | 
        String xTenantID = "xTenantID_example"; // String | 
        String xUserID = "xUserID_example"; // String | 
        String authToken = "authToken_example"; // String | 
        try {
            ModelApiResponse result = apiInstance.getSampleById(id, xTenantID, xUserID, authToken);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SampleControllerApi#getSampleById");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **xTenantID** | **String**|  | |
| **xUserID** | **String**|  | |
| **authToken** | **String**|  | |

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |


## updateSample

> ModelApiResponse updateSample(id, xTenantID, xUserID, authToken, sample)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.SampleControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/myservice-service");

        SampleControllerApi apiInstance = new SampleControllerApi(defaultClient);
        Long id = 56L; // Long | 
        String xTenantID = "xTenantID_example"; // String | 
        String xUserID = "xUserID_example"; // String | 
        String authToken = "authToken_example"; // String | 
        Sample sample = new Sample(); // Sample | 
        try {
            ModelApiResponse result = apiInstance.updateSample(id, xTenantID, xUserID, authToken, sample);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SampleControllerApi#updateSample");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**|  | |
| **xTenantID** | **String**|  | |
| **xUserID** | **String**|  | |
| **authToken** | **String**|  | |
| **sample** | [**Sample**](Sample.md)|  | |

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

