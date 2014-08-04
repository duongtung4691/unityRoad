package com.example.nhacchuonghay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

public class JsonParser {
	InputStream in = null;
	JSONObject ojbj = null;
	String json;
public JsonParser()
{
	
}
	public JSONObject getJSONFromUrl(String url) {
		// khoi tao

		// thuc thi lay ve noi dung
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost http = new HttpPost(url);
			
			HttpResponse httpresponse = client.execute(http);
			HttpEntity httpentity = httpresponse.getEntity();
			in = httpentity.getContent();
			// doc du lieu
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"), 8);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			in.close();
			json = sb.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("chan bo me","nan"+e.toString());
		}
	 try {
	         ojbj = new JSONObject(json);
	    } catch (JSONException e) {
	          Log.e("JSON Parser", "Error parsing data " + e.toString());
	      }
		return ojbj;
	}
}
