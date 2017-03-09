package cn.itcast.wh.p2pmoney12.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.bean.Product;
import cn.itcast.wh.p2pmoney12.common.AppNetConfig;
import cn.itcast.wh.p2pmoney12.common.BaseHolder;
import cn.itcast.wh.p2pmoney12.common.MyBaseAdapter2;
import cn.itcast.wh.p2pmoney12.ui.RoundProgress;
import cn.itcast.wh.p2pmoney12.util.UIUtils;

/**
 * Created by Administrator on 2015/12/16.
 */
public class ProductListFragment extends Fragment {

    @Bind(R.id.lv)
    ListView lv;

    private AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        ButterKnife.bind(this, view);
        initTilte();
        initData();
        return view;
    }

    private void initData() {
        client.post(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.getBoolean("success")) {
                    String data = jsonObject.getString("data");
                    List<Product> products = JSON.parseArray(data, Product.class);
                    lv.setAdapter(new MyAdapter3(products));
                }
                ;
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });
    }

    private void initTilte() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

//    private class MyAdapter extends BaseAdapter {
//
//        private List<Product> products;
//
//        public MyAdapter(List<Product> products) {
//            this.products = products;
//        }
//
//        @Override
//        public int getCount() {
//            return products == null ? 0 : products.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return products.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            Product product = products.get(position);
//            ViewHolder viewHolder = null;
//            if (convertView == null) {
//                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
//                viewHolder = new ViewHolder(convertView);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            //设置数据
//            viewHolder.pMinzouzi.setText(product.minTouMoney);
//            viewHolder.pMoney.setText(product.money);
//            viewHolder.pName.setText(product.name);
//            viewHolder.pSuodingdays.setText(product.suodingDays);
//            viewHolder.pYearlv.setText(product.yearLv);
//            viewHolder.pProgresss.setProgress(Integer.parseInt(product.progress));
//            return convertView;
//        }
//    }
//
//
//    private class MyAdapter2 extends MySimpleBaseAdapter<Product> {
//
//        public MyAdapter2(List<Product> list) {
//            super(list);
//        }
//
//        @Override
//        public View getYourView(int position, View convertView, ViewGroup parent) {
//            Product product = list.get(position);
//            ViewHolder viewHolder = null;
//            if (convertView == null) {
//                convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
//                viewHolder = new ViewHolder(convertView);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            //设置数据
//            viewHolder.pMinzouzi.setText(product.minTouMoney);
//            viewHolder.pMoney.setText(product.money);
//            viewHolder.pName.setText(product.name);
//            viewHolder.pSuodingdays.setText(product.suodingDays);
//            viewHolder.pYearlv.setText(product.yearLv);
//            viewHolder.pProgresss.setProgress(Integer.parseInt(product.progress));
//            return convertView;
//        }
//    }


    private class MyAdapter3 extends MyBaseAdapter2<Product> {

        public MyAdapter3(List<Product> list) {
            super(list);
        }

        @Override
        protected BaseHolder getHolder() {
            return new MyViewHolder();
        }
    }

    class MyViewHolder extends BaseHolder<Product> {

        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        @Override
        public View initView() {
            View inflate = View.inflate(getActivity(), R.layout.item_product_list, null);
            return inflate;
        }

        @Override
        protected void refreshView() {
            Product product = getData();
            //设置数据
            pMinzouzi.setText(product.minTouMoney);
            pMoney.setText(product.money);
            pName.setText(product.name);
            pSuodingdays.setText(product.suodingDays);
            pYearlv.setText(product.yearLv);
            pProgresss.setProgress(Integer.parseInt(product.progress));
        }
    }


//    /**
//     * This class contains all butterknife-injected Views & Layouts from layout file 'item_product_list.xml'
//     * for easy to all layout elements.
//     *
//     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
//     */
//    static class ViewHolder {
//        @Bind(R.id.p_name)
//        TextView pName;
//        @Bind(R.id.p_money)
//        TextView pMoney;
//        @Bind(R.id.p_yearlv)
//        TextView pYearlv;
//        @Bind(R.id.p_suodingdays)
//        TextView pSuodingdays;
//        @Bind(R.id.p_minzouzi)
//        TextView pMinzouzi;
//        @Bind(R.id.p_progresss)
//        RoundProgress pProgresss;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
}
