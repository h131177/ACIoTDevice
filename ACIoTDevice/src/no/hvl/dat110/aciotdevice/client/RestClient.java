package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.*;
import okhttp3.Request;
import okhttp3.Response;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		OkHttpClient okClient = new OkHttpClient();
		Gson gson = new Gson();
		AccessMessage aMsg = new AccessMessage(message);
		RequestBody reqBody = RequestBody.create(JSON, gson.toJson(aMsg));

		Request request = new Request.Builder()
				.url("http://localhost:8080/accessdevice/log/")
				.post(reqBody)
				.build();

		System.out.println(request);

		try (Response response = okClient.newCall(request).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		OkHttpClient okClient = new OkHttpClient();
		Gson gson = new Gson();

		Request request = new Request.Builder()
				.url("http://localhost:8080/accessdevice/code")
				.get()
				.build();

		System.out.println(request);

		try (Response response = okClient.newCall(request).execute()) {
			String resBody = response.body().string();
			System.out.println(resBody);
			code = gson.fromJson(resBody, AccessCode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
}
