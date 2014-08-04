package com.example.nhacchuonghay;

import java.util.ArrayList;
import customlist.BaiHat;
import customlist.CustomAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Contacts.Intents;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {
	ArrayList<BaiHat> arrBH = new ArrayList<BaiHat>();
	CustomAdapter AdapterNew;
	private TextView quit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ListView lvchon = (ListView) findViewById(R.id.listView1);
		quit = (TextView) findViewById(R.id.header_tv_next);
		quit.setText("Thoát");
		quit.setVisibility(View.VISIBLE);
		quit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Main.this.finish();
			}
		});
		try {
			BaiHat bh1 = new BaiHat();
			bh1.Ten = "Nhạc Trẻ";
			bh1.HinhAnh = BitmapFactory.decodeResource(getResources(),
					R.drawable.music);
			bh1.ThoiLuong = "01:30";
			arrBH.add(bh1);

			BaiHat bh2 = new BaiHat();
			bh2.Ten = "Nhạc  Độc";
			bh2.ThoiLuong = "1:30";
			bh2.HinhAnh = BitmapFactory.decodeResource(getResources(),
					R.drawable.music);
			arrBH.add(bh2);

			BaiHat bh3 = new BaiHat();
			bh3.Ten = "Nhạc  Theo Tên";
			bh3.ThoiLuong = "5:30";
			bh3.HinhAnh = BitmapFactory.decodeResource(getResources(),
					R.drawable.music);

			arrBH.add(bh3);
			BaiHat bh4 = new BaiHat();
			bh4.Ten = "Nhạc Hàn Quốc";
			bh4.ThoiLuong = "5:30";
			bh4.HinhAnh = BitmapFactory.decodeResource(getResources(),
					R.drawable.music);
			arrBH.add(bh4);
			BaiHat bh5 = new BaiHat();
			bh5.Ten = "Nhạc Tiếng Anh";
			bh5.ThoiLuong = "5:30";
			bh5.HinhAnh = BitmapFactory.decodeResource(getResources(),
					R.drawable.music);
			arrBH.add(bh5);
			BaiHat bh6 = new BaiHat();
			bh6.Ten = "Nhạc Quê Hương";
			bh6.ThoiLuong = "5:30";
			bh6.HinhAnh = BitmapFactory.decodeResource(getResources(),
					R.drawable.music);
			arrBH.add(bh5);
			AdapterNew = new CustomAdapter(this, R.layout.activity_customlist,
					arrBH);
			lvchon.setAdapter(AdapterNew);

		} catch (Exception ex) {
			System.out.println("Lỗi" + ex.getMessage());

		}
		lvchon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 0) {
					String theloai1 = "1";
					Intent inten = new Intent(Main.this, MainActivity.class);
					inten.putExtra("theloai", theloai1);
					Main.this.finish();
					startActivity(inten);
				}
				if (arg2 == 1) {
					String theloai1 = "2";
					Intent inten = new Intent(Main.this, MainActivity.class);
					inten.putExtra("theloai", theloai1);
					Main.this.finish();
					startActivity(inten);
				}
				if (arg2 == 2) {
					String theloai1 = "8";
					Intent inten = new Intent(Main.this, MainActivity.class);
					inten.putExtra("theloai", theloai1);
					Main.this.finish();
					startActivity(inten);
				}
				if (arg2 == 3) {
					String theloai1 = "3";
					Intent inten = new Intent(Main.this, MainActivity.class);
					inten.putExtra("theloai", theloai1);
					Main.this.finish();
					startActivity(inten);
				}
				if (arg2 == 4) {
					String theloai1 = "5";
					Intent inten = new Intent(Main.this, MainActivity.class);
					inten.putExtra("theloai", theloai1);
					Main.this.finish();
					startActivity(inten);
				}
				if (arg2 == 5) {
					String theloai1 = "6";
					Intent inten = new Intent(Main.this, MainActivity.class);
					inten.putExtra("theloai", theloai1);
					Main.this.finish();
					startActivity(inten);
				}
			}
		});
	}
}
