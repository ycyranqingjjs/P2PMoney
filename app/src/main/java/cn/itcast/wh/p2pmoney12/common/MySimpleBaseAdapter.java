package cn.itcast.wh.p2pmoney12.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public abstract class MySimpleBaseAdapter<T> extends BaseAdapter {

    protected List<T> list;

    public MySimpleBaseAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getYourView(position,convertView,parent);
    }

    public abstract View getYourView(int position, View convertView, ViewGroup parent);
}
