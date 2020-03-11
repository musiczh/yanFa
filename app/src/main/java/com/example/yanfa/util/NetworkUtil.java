package com.example.yanfa.util;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
 
public class NetworkUtil {
 
	/**
	 * 检查网络是否可用
	 * 
	 * @param context 传递上下文
	 * @return 返回是否有网络
	 */
	public static boolean isNetworkAvailable(Context context) {
 
		ConnectivityManager manager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
 
		if (manager == null) {
			return false;
		}
 
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();

		return networkinfo != null && networkinfo.isAvailable();
	}
	
}