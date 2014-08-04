package customlist;

import java.util.ArrayList;

import com.example.nhacchuonghay.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<BaiHat>{
	
	
	Activity context;
	int layoutId; 
	public ArrayList<BaiHat> ListBaiHat=null;
	public CustomAdapter(Activity context, int layoutID, ArrayList<BaiHat> ds) {
		super(context, layoutID,ds);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.layoutId=layoutID;
		this.ListBaiHat=ds;
	
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		 View v=convertView;
		if(v==null){
			v = LayoutInflater.from(context).inflate(layoutId, null);

		}
			
			BaiHat pos = ListBaiHat.get(position);
			
			TextView Tentextview = (TextView)v.findViewById(R.id.TenBh_textView1);
			Tentextview.setText(pos.GetTen());
			TextView Linktextview = (TextView)v.findViewById(R.id.LinkBH_textView2);
			Linktextview.setText(pos.GetLink());
			 ImageView img=(ImageView)v.findViewById(R.id.imageView1);
			 img.setImageBitmap(pos.GetHinhAnh());
			return v;
	}
}
