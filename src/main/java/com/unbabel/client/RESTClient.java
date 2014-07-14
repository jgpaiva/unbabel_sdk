package com.unbabel.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.unbabel.sdk.exceptions.ApiException;
import com.unbabel.sdk.exceptions.BadRequestException;
import com.unbabel.sdk.exceptions.MarshalingException;
import com.unbabel.sdk.exceptions.UnauthorizedException;

public class RESTClient {
	public static String callRestService(HttpRequestBase request)
			throws ApiException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			request.addHeader("Content-Type", "application/json");

			request.addHeader("Accept", "application/json");

			CloseableHttpResponse response = httpclient.execute(request);

			HttpEntity entity = response.getEntity();
			int statusCode = response.getStatusLine().getStatusCode();
			String respBody = IOUtils.toString(entity.getContent(),"UTF-8");

			try {
				if (statusCode == HttpURLConnection.HTTP_OK) {
					return respBody;
				} else if (statusCode == HttpURLConnection.HTTP_CREATED) {
					return respBody;
				} else if (statusCode == HttpURLConnection.HTTP_ACCEPTED) {
					return respBody;
				} else if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
					throw new UnauthorizedException(response.getStatusLine()
							+ " " + respBody);
				} else if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST) {
					throw new BadRequestException(response.getStatusLine()
							+ " " + respBody);
				} else
					throw new ApiException(response.getStatusLine() + " "
							+ respBody);
			} finally {
				// ensure it is fully consumed
				EntityUtils.consume(entity);
			}

		} catch (IOException e) {
			throw new ApiException("Connection error", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// ignored
			}
		}
	}

	public static String get(String url, String authorization) {
		HttpGet request = new HttpGet(url);
		request.addHeader("Authorization", authorization);
		return callRestService(request);
	}

	public static String post(String url, String authorization, String data) {
		HttpPost request = new HttpPost(url);
		try {
			request.setEntity(new StringEntity(data));
		} catch (UnsupportedEncodingException e) {
			throw new MarshalingException("Error encoding POST request", e);
		}
		request.addHeader("Authorization", authorization);
		return callRestService(request);
	}

	public static String patch(String url, String authorization, String data) {
		HttpPatch request = new HttpPatch(url);
		try {
			request.setEntity(new StringEntity(data));
		} catch (UnsupportedEncodingException e) {
			throw new MarshalingException("Error encoding PATCH request", e);
		}
		request.addHeader("Authorization", authorization);
		return callRestService(request);
	}
}
