package cn.itcast.wh.p2pmoney12.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.bean.Image;
import cn.itcast.wh.p2pmoney12.bean.Index;
import cn.itcast.wh.p2pmoney12.bean.Product;
import cn.itcast.wh.p2pmoney12.common.AppNetConfig;
import cn.itcast.wh.p2pmoney12.common.BaseFragment;
import cn.itcast.wh.p2pmoney12.ui.MyScrollview;
import cn.itcast.wh.p2pmoney12.ui.RoundProgress;

/**
 * Created by Administrator on 2015/12/11.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.vp_barner)
    ViewPager vpBarner;
    @Bind(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.p_progresss)
    RoundProgress pProgresss;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.myscrollview)
    MyScrollview myscrollview;


    private Index index;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void initData(String content) {
        if (!TextUtils.isEmpty(content)) {
            index = new Index();
            JSONObject jsonObject = JSON.parseObject(content);
            String proInfo = jsonObject.getString("proInfo");
            Product product = JSON.parseObject(proInfo, Product.class);
            String imageArr = jsonObject.getString("imageArr");
            List<Image> imageList = JSON.parseArray(imageArr, Image.class);
            index.product = product;
            index.imageList = imageList;
            //适配数据
            vpBarner.setAdapter(new MyAdapter());
            //viewpager交给指示器
            circleBarner.setViewPager(vpBarner);
            totalProgress = Integer.parseInt(index.product.progress);
            new Thread(runnable).start();
        }
    }

    @Override
    protected void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    private int totalProgress;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int tempProgress = 0;
            try {
                while (tempProgress <= totalProgress) {
                    pProgresss.setProgress(tempProgress);
                    tempProgress++;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return index.imageList == null ? 0 : index.imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = index.imageList.get(position).IMAURL;
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(imageUrl).into(imageView);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
