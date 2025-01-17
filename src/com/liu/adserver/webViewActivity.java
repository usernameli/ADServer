package com.liu.adserver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class webViewActivity extends Activity {
	MapView mMapView = null;
	BaiduMap baiduMap;
	TextView jingdu;
	TextView weidu;
	Button queding;
	double jingdutext;
	double weidutext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.web_act);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		jingdu = (TextView) findViewById(R.id.act_jingdu);
		weidu = (TextView) findViewById(R.id.act_weidu);
		queding = (Button) findViewById(R.id.act_queding);
		queding.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(webViewActivity.this,
						MainActivity.class);
				intent.putExtra("jingdu", jingdutext);
				intent.putExtra("weidu", weidutext);
				startActivity(intent);
			}
		});
		baiduMap = mMapView.getMap();

		OnMapClickListener listener = new OnMapClickListener() {
			/**
			 * 地图单击事件回调函数
			 * 
			 * @param point
			 *            点击的地理坐标
			 */
			public void onMapClick(LatLng point) {
				System.out.println(point.latitude + "  " + point.longitude);
				jingdu.setText("经度：" + point.longitude);
				jingdutext = point.longitude;
				weidutext = point.latitude;
				baiduMap.clear();
				weidu.setText("纬度:" + point.latitude);
				// 定义Maker坐标点
				// LatLng point = new LatLng(39.963175, 116.400244);
				// 构建Marker图标
				BitmapDescriptor bitmap = BitmapDescriptorFactory
						.fromResource(R.drawable.loca);
				// 构建MarkerOption，用于在地图上添加Marker
				OverlayOptions option = new MarkerOptions().position(point)
						.icon(bitmap);
				// 在地图上添加Marker，并显示
				baiduMap.addOverlay(option);
			}

			/**
			 * 地图内 Poi 单击事件回调函数
			 * 
			 * @param poi
			 *            点击的 poi 信息
			 */
			public boolean onMapPoiClick(MapPoi poi) {
				return false;
			}

		};
		baiduMap.setOnMapClickListener(listener);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}
}