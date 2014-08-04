package customlist;

import android.graphics.Bitmap;

public class BaiHat {
	public String Ten;
	public String ThoiLuong;
	public Bitmap HinhAnh;
	public  String GetTen() {
		return Ten;
	}
	public  String GetLink() {
		return ThoiLuong;
	}
	public  Bitmap GetHinhAnh() {
		return HinhAnh;
	}
	public void setTen(String ten) {
		this.Ten = ten;
	}
	public void setLink(String Thoiluong) {
		this.ThoiLuong = Thoiluong;
	}
	public void setHinh(Bitmap Hinh) {
		this.HinhAnh = Hinh;
	}
}
