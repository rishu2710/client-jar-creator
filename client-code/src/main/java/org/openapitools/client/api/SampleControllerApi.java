package org.openapitools.client.api;

import org.openapitools.client.ApiClient;

import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.Sample;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-20T17:57:09.906055+05:30[Asia/Calcutta]", comments = "Generator version: 7.14.0")
public class SampleControllerApi {
    private ApiClient apiClient;

    public SampleControllerApi() {
        this(new ApiClient());
    }

    public SampleControllerApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    
    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createSampleRequestCreation(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        Object postBody = sample;
        // verify the required parameter 'xTenantID' is set
        if (xTenantID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xTenantID' when calling createSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xUserID' is set
        if (xUserID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xUserID' when calling createSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'authToken' is set
        if (authToken == null) {
            throw new WebClientResponseException("Missing the required parameter 'authToken' when calling createSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'sample' is set
        if (sample == null) {
            throw new WebClientResponseException("Missing the required parameter 'sample' when calling createSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


        if (xTenantID != null)
        headerParams.add("X-Tenant-ID", apiClient.parameterToString(xTenantID));
        if (xUserID != null)
        headerParams.add("X-User-ID", apiClient.parameterToString(xUserID));
        if (authToken != null)
        headerParams.add("Auth_Token", apiClient.parameterToString(authToken));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return apiClient.invokeAPI("/sample", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelApiResponse> createSample(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return createSampleRequestCreation(xTenantID, xUserID, authToken, sample).bodyToMono(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ResponseEntity&lt;ModelApiResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelApiResponse>> createSampleWithHttpInfo(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return createSampleRequestCreation(xTenantID, xUserID, authToken, sample).toEntity(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createSampleWithResponseSpec(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        return createSampleRequestCreation(xTenantID, xUserID, authToken, sample);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteSampleRequestCreation(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling deleteSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xTenantID' is set
        if (xTenantID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xTenantID' when calling deleteSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xUserID' is set
        if (xUserID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xUserID' when calling deleteSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'authToken' is set
        if (authToken == null) {
            throw new WebClientResponseException("Missing the required parameter 'authToken' when calling deleteSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


        if (xTenantID != null)
        headerParams.add("X-Tenant-ID", apiClient.parameterToString(xTenantID));
        if (xUserID != null)
        headerParams.add("X-User-ID", apiClient.parameterToString(xUserID));
        if (authToken != null)
        headerParams.add("Auth_Token", apiClient.parameterToString(authToken));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return apiClient.invokeAPI("/sample/{id}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelApiResponse> deleteSample(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return deleteSampleRequestCreation(id, xTenantID, xUserID, authToken).bodyToMono(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ResponseEntity&lt;ModelApiResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelApiResponse>> deleteSampleWithHttpInfo(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return deleteSampleRequestCreation(id, xTenantID, xUserID, authToken).toEntity(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteSampleWithResponseSpec(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        return deleteSampleRequestCreation(id, xTenantID, xUserID, authToken);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getAllSamplesRequestCreation(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'xTenantID' is set
        if (xTenantID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xTenantID' when calling getAllSamples", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xUserID' is set
        if (xUserID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xUserID' when calling getAllSamples", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'authToken' is set
        if (authToken == null) {
            throw new WebClientResponseException("Missing the required parameter 'authToken' when calling getAllSamples", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


        if (xTenantID != null)
        headerParams.add("X-Tenant-ID", apiClient.parameterToString(xTenantID));
        if (xUserID != null)
        headerParams.add("X-User-ID", apiClient.parameterToString(xUserID));
        if (authToken != null)
        headerParams.add("Auth_Token", apiClient.parameterToString(authToken));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return apiClient.invokeAPI("/sample", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelApiResponse> getAllSamples(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return getAllSamplesRequestCreation(xTenantID, xUserID, authToken).bodyToMono(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ResponseEntity&lt;ModelApiResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelApiResponse>> getAllSamplesWithHttpInfo(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return getAllSamplesRequestCreation(xTenantID, xUserID, authToken).toEntity(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getAllSamplesWithResponseSpec(@jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        return getAllSamplesRequestCreation(xTenantID, xUserID, authToken);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getSampleByIdRequestCreation(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling getSampleById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xTenantID' is set
        if (xTenantID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xTenantID' when calling getSampleById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xUserID' is set
        if (xUserID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xUserID' when calling getSampleById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'authToken' is set
        if (authToken == null) {
            throw new WebClientResponseException("Missing the required parameter 'authToken' when calling getSampleById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


        if (xTenantID != null)
        headerParams.add("X-Tenant-ID", apiClient.parameterToString(xTenantID));
        if (xUserID != null)
        headerParams.add("X-User-ID", apiClient.parameterToString(xUserID));
        if (authToken != null)
        headerParams.add("Auth_Token", apiClient.parameterToString(authToken));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return apiClient.invokeAPI("/sample/{id}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelApiResponse> getSampleById(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return getSampleByIdRequestCreation(id, xTenantID, xUserID, authToken).bodyToMono(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ResponseEntity&lt;ModelApiResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelApiResponse>> getSampleByIdWithHttpInfo(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return getSampleByIdRequestCreation(id, xTenantID, xUserID, authToken).toEntity(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getSampleByIdWithResponseSpec(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken) throws WebClientResponseException {
        return getSampleByIdRequestCreation(id, xTenantID, xUserID, authToken);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateSampleRequestCreation(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        Object postBody = sample;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling updateSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xTenantID' is set
        if (xTenantID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xTenantID' when calling updateSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'xUserID' is set
        if (xUserID == null) {
            throw new WebClientResponseException("Missing the required parameter 'xUserID' when calling updateSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'authToken' is set
        if (authToken == null) {
            throw new WebClientResponseException("Missing the required parameter 'authToken' when calling updateSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'sample' is set
        if (sample == null) {
            throw new WebClientResponseException("Missing the required parameter 'sample' when calling updateSample", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();


        if (xTenantID != null)
        headerParams.add("X-Tenant-ID", apiClient.parameterToString(xTenantID));
        if (xUserID != null)
        headerParams.add("X-User-ID", apiClient.parameterToString(xUserID));
        if (authToken != null)
        headerParams.add("Auth_Token", apiClient.parameterToString(authToken));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return apiClient.invokeAPI("/sample/{id}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ModelApiResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ModelApiResponse> updateSample(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return updateSampleRequestCreation(id, xTenantID, xUserID, authToken, sample).bodyToMono(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ResponseEntity&lt;ModelApiResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ModelApiResponse>> updateSampleWithHttpInfo(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        ParameterizedTypeReference<ModelApiResponse> localVarReturnType = new ParameterizedTypeReference<ModelApiResponse>() {};
        return updateSampleRequestCreation(id, xTenantID, xUserID, authToken, sample).toEntity(localVarReturnType);
    }

    /**
     * 
     * 
     * <p><b>200</b> - OK
     * @param id The id parameter
     * @param xTenantID The xTenantID parameter
     * @param xUserID The xUserID parameter
     * @param authToken The authToken parameter
     * @param sample The sample parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateSampleWithResponseSpec(@jakarta.annotation.Nonnull Long id, @jakarta.annotation.Nonnull String xTenantID, @jakarta.annotation.Nonnull String xUserID, @jakarta.annotation.Nonnull String authToken, @jakarta.annotation.Nonnull Sample sample) throws WebClientResponseException {
        return updateSampleRequestCreation(id, xTenantID, xUserID, authToken, sample);
    }
}
