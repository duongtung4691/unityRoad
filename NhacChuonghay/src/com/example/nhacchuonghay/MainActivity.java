package com.example.nhacchuonghay;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String url = "http://vertex-vietnam.com/hoangnguyen/Handler.ashx?type=selectlist";
	String Tag_datas = "data";
	String Tag_id = "ID";
	String Tag_name = "RingName";
	String Tag_Link = "Link";
	String Tag_Genre = "Genre";
	JSONArray data = null;
	String ten;
	String theloai;
	SimpleAdapter adapter;
	private ProgressDialog pDialog;
	ArrayList<HashMap<String, String>> contastlist = new ArrayList<HashMap<String, String>>();
	ListView lv;
	public static final int progress_bar_type = 0;
	private static final Context context = null;
	EditText timkiem;
	private TextView quit, bank, title;
	private static final int DIALOG_ALERT = 10;
	String filepath ="/sdcard/nhacchuonghot/";
	File ringtoneFile = new File(filepath);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity1);
		new thucthilogin().execute();
		lv = (ListView) findViewById(R.id.nhachot_listView1);

		theloai = getIntent().getExtras().getString("theloai");

		timkiem = (EditText) findViewById(R.id.timkiem_edittext);
		// //////////////////////////////////////////////////
		title = (TextView) findViewById(R.id.header_tv_title);
		quit = (TextView) findViewById(R.id.header_tv_next);
		bank = (TextView) findViewById(R.id.header_tv_back);
		quit.setText("Thoát");
		bank.setText("Quay lại");
		quit.setVisibility(View.VISIBLE);
		bank.setVisibility(View.VISIBLE);
		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});
		bank.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent bank = new Intent(MainActivity.this, Main.class);
				startActivity(bank);
				MainActivity.this.finish();
			}
		});
		// //////////////////////////////////////////////////
		File direct = new File(Environment.getExternalStorageDirectory()
				+ "/nhacchuonghot");
		if (!direct.exists()) {
			if (direct.mkdir()) {
			}

		}
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String link = ((TextView) arg1
						.findViewById(R.id.LinkBH_textView2)).getText()
						.toString();
				ten = ((TextView) arg1.findViewById(R.id.TenBh_textView1))
						.getText().toString();
				new DownloadFileFromURL().execute(link);
				////////////////
		    
				//////////////////
			}
		});
		timkiem.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				adapter.getFilter().filter(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_activity1, menu);
		return true;
	}

	class thucthilogin extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			JsonParser json = new JsonParser();
			JSONObject jsonjbject = json.getJSONFromUrl(url);
			try {
				data = jsonjbject.getJSONArray(Tag_datas);
				for (int i = 0; i < data.length(); i++) {
					JSONObject onetcontac = data.getJSONObject(i);
					String id = String.valueOf(onetcontac.getInt(Tag_id));
					String name = onetcontac.getString(Tag_name);
					String Link = onetcontac.getString(Tag_Link);
					String Genre = onetcontac.getString(Tag_Genre);
					// Log.e("JSON NE", "dsfds" + name);
					if (Genre.equals(theloai)) {

						HashMap<String, String> map = new HashMap<String, String>();
						map.put(Tag_id, id);
						map.put(Tag_name, name);
						map.put(Tag_Link, Link);
						map.put(Tag_Genre, Genre);
						contastlist.add(map);
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void thanhcong) {

			String[] from = new String[] { Tag_name, Tag_Link };
			int[] to = new int[] { R.id.TenBh_textView1, R.id.LinkBH_textView2 };
			adapter = new SimpleAdapter(getApplicationContext(), contastlist,
					R.layout.activity_customlist, from, to);
			lv.setAdapter(adapter);

		}

	}

	// //////////////////
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case progress_bar_type:
			pDialog = new ProgressDialog(this);
			pDialog.setMessage("Downloading file. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setMax(100);
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.setCancelable(true);
			pDialog.show();
			return pDialog;
		default:
			return null;
			// ////////////////

			// ////////////////
		}
	}

	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(progress_bar_type);

			//////////////////
			
			/////////////////////
		}

		@Override
		protected String doInBackground(String... f_url) {
			int count;
			try {
				URL url = new URL(f_url[0]);
				URLConnection conection = url.openConnection();
				conection.connect();
				// getting file length
				int lenghtOfFile = conection.getContentLength();

				// input stream to read file - with 8k buffer
				InputStream input = new BufferedInputStream(url.openStream(),
						8192);

				// Output stream to write file
				OutputStream output = new FileOutputStream(
						"/sdcard/nhacchuonghot/" + ten + ".mp3");
				byte data[] = new byte[1024];
				long total = 0;
				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();

			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}

			return null;
		}

		protected void onProgressUpdate(String... progress) {
			pDialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String file_url) {
			dismissDialog(progress_bar_type);

		
	
			String filepath ="/sdcard/nhacchuonghot/"+ten+".mp3";
			try{
				MediaPlayer mp= new MediaPlayer();
				mp.setDataSource(filepath);
				mp.prepare();
				mp.start();
				AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
		        MainActivity.this);
		 
		// Setting Dialog Title
		alertDialog2.setTitle("Thông Báo...");
		 
		// Setting Dialog Message
		alertDialog2.setMessage("Bạn Có Muốn Đăt Nhạc Làm Nhạc Chuông ?");
		 
		// Setting Icon to Dialog
		alertDialog2.setIcon(R.drawable.ic_launcher);
		 
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton("YES",
		        new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		                // Write your code here to execute after dialog
		    			String filepath ="/sdcard/nhacchuonghot/"+ten+".mp3";

		                File ringtoneFile = new File(filepath);

		                ContentValues content = new ContentValues();
		                content.put(MediaStore.MediaColumns.DATA,ringtoneFile.getAbsolutePath());
		                content.put(MediaStore.MediaColumns.TITLE, "chinnu");
		                content.put(MediaStore.MediaColumns.SIZE, 215454);
		                content.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*");
		                content.put(MediaStore.Audio.Media.ARTIST, "Madonna");
		                content.put(MediaStore.Audio.Media.DURATION, 230);
		                content.put(MediaStore.Audio.Media.IS_RINGTONE, true);
		                content.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
		                content.put(MediaStore.Audio.Media.IS_ALARM, false);
		                content.put(MediaStore.Audio.Media.IS_MUSIC, false);


		                //Insert it into the database
		                Uri uri = MediaStore.Audio.Media.getContentUriForPath(ringtoneFile.getAbsolutePath());
		                //Uri newUri = context.getContentResolver().insert(uri, content);
		               // Uri newUri = getBaseContext().getContentResolver().insert(uri, content); 
		                //String ringtoneUri = newUri.toString();
		                getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + ringtoneFile.getAbsolutePath() + "\"", null);
		                Uri newUri = getContentResolver().insert(uri, content);

		                RingtoneManager.setActualDefaultRingtoneUri(getBaseContext(),RingtoneManager.TYPE_RINGTONE,newUri);
		            	
		            }
		        });
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton("NO",
		        new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		                // Write your code here to execute after dialog
		                Toast.makeText(getApplicationContext(),
		                        "You clicked on NO", Toast.LENGTH_SHORT)
		                        .show();
		                dialog.cancel();
		            }
		        });
		 
		// Showing Alert Dialog
		alertDialog2.show();
				} catch (Exception ex){
					Log.e("anh yeu em","chan");
				}
				
		
	}
	}
		}


