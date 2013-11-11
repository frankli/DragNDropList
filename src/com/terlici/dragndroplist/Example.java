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
			public View getViewAtRawPosition(int position, View convertView, ViewGroup arg2) {
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				}
				((TextView)convertView.findViewById(R.id.text_line1)).setText("" + position);
				((TextView)convertView.findViewById(R.id.text_line2)).setText("" + position);
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
			long getItemIdAtRawPosition(int i) {
				return i;
			}

			@Override
			Object getItemAtRawPosition(int i) {
				return null;
			}

		});
	}
}
