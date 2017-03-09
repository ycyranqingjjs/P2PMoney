package cn.itcast.wh.p2pmoney12.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public abstract class MyBaseAdapter2<T> extends BaseAdapter {

    protected List<T> list;

    public MyBaseAdapter2(List<T> list) {
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
        BaseHolder holder = null;
        if(convertView==null){
            holder = getHolder();
        }else{
            holder = (BaseHolder) convertView.getTag();
        }
        //设置数据
        holder.setData(list.get(position));
        return holder.getRootView();
    }

    protected abstract BaseHolder getHolder();
}
