package com.terlici.dragndroplist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Example extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DragNDropListView list = (DragNDropListView) findViewById(android.R.id.list);
		list.setDragNDropAdapter(new BaseDragDropAdapter() {
			
			@Override
			public View getViewAtRawPosition(int raw, int actual, View convertView, ViewGroup arg2) {
				convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				((TextView)convertView.findViewById(R.id.text_line1)).setText("" + raw);
				((TextView)convertView.findViewById(R.id.text_line2)).setText("" + raw);
				return convertView;
			}
			
			@Override
			public int getCount() {
				return 10;
			}
			
			@Override
			public int getDragHandler() {
				return R.id.drag_handle;
			}

			@Override
			public long getItemIdAtRawPosition(int raw, int actual) {
				return raw;
			}

			@Override
			public Object getItemAtRawPosition(int raw, int actual) {
				return null;
			}

			@Override
			public View getEmptySpaceView(int position, View convertView,
					ViewGroup parent) {
				convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				return convertView;
			}

		});
	}
}
