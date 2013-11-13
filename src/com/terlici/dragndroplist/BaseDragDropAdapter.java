package com.terlici.dragndroplist;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class BaseDragDropAdapter extends BaseAdapter implements
		DragNDropAdapter {

	private int[] mPosition;
	private int[] startPositionArray;
	private int lastEndPosition = -1;

	public BaseDragDropAdapter() {
		setup(getCount());
	}

	private void setup(int size) {
		mPosition = new int[size];
		for (int i = 0; i < size; ++i)
			mPosition[i] = i;
	}

	@Override
	public void onItemDrag(DragNDropListView parent, View view, int position,
			long id) {
		startPositionArray = new int[mPosition.length];
		for (int i = 0; i < mPosition.length; i++) {
			startPositionArray[i] = mPosition[i];
		}
		lastEndPosition = -1;
	}

	@Override
	public void onItemHover(DragNDropListView parent, View view,
			int startPosition, int endPosition, long id) {
		if (endPosition != lastEndPosition) {
			lastEndPosition = endPosition;
			Log.i("test", "hover " + startPosition + " " + endPosition + " " + id);
			for (int i = 0; i < mPosition.length; i++) {
				mPosition[i] = startPositionArray[i];
			}
			int position = mPosition[startPosition];

			if (startPosition < endPosition)
				for (int i = startPosition; i < endPosition; ++i)
					mPosition[i] = mPosition[i + 1];
			else if (endPosition < startPosition)
				for (int i = startPosition; i > endPosition; --i)
					mPosition[i] = mPosition[i - 1];

			mPosition[endPosition] = position;
			parent.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			parent.setSelection(endPosition);
			notifyDataSetChanged();
		}
	}

	@Override
	public void onItemDrop(DragNDropListView parent, View view,
			int startPosition, int endPosition, long id) {
		// swap start and end position
		for (int i = 0; i < mPosition.length; i++) {
			mPosition[i] = startPositionArray[i];
		}
		int position = mPosition[startPosition];

		if (startPosition < endPosition)
			for (int i = startPosition; i < endPosition; ++i)
				mPosition[i] = mPosition[i + 1];
		else if (endPosition < startPosition)
			for (int i = startPosition; i > endPosition; --i)
				mPosition[i] = mPosition[i - 1];

		mPosition[endPosition] = position;
		lastEndPosition = -1;
	}

	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		View view = getViewAtRawPosition(mPosition[position], convertView, parent);
		if (position == lastEndPosition) {
			view.setBackgroundColor(0);
		} else {
			view.setBackgroundColor(0x66ffffff);
		}
		Log.i("test", "get view " + position + " " + lastEndPosition + " " + view.getVisibility());
		return view;
	}

	@Override
	public final long getItemId(int position) {
		return getItemIdAtRawPosition(mPosition[position]);
	}

	@Override
	public final Object getItem(int position) {
		return getItemAtRawPosition(mPosition[position]);
	}

	public abstract long getItemIdAtRawPosition(int i);

	public abstract Object getItemAtRawPosition(int i);

	public abstract View getViewAtRawPosition(int i, View convertView, ViewGroup parent);
}
