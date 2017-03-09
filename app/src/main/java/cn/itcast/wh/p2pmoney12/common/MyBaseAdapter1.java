package cn.itcast.wh.p2pmoney12.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/12/16.
 */
public abstract class MyBaseAdapter1<T> extends BaseAdapter {

    protected List<T> list;

    public MyBaseAdapter1(List<T> list) {
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
        T t = list.get(position);
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = initView();
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        setData(convertView,t);
        return convertView;
    }

    protected abstract void setData(View convertView, T t);

    protected abstract View initView();

    static class ViewHolder{

        public ViewHolder(View convertView) {
            convertView.setTag(this);
        }
    }
}
