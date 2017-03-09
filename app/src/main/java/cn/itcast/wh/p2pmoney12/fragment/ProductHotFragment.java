package cn.itcast.wh.p2pmoney12.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.ui.FlowLayout;
import cn.itcast.wh.p2pmoney12.util.DrawableUtil;
import cn.itcast.wh.p2pmoney12.util.UIUtils;

/**
 * Created by Administrator on 2015/12/16.
 */
public class ProductHotFragment extends Fragment {

    @Bind(R.id.flow)
    FlowLayout flow;

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};
    private Random random;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_hot);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        random = new Random();
        for (String data : datas) {
            TextView tv = new TextView(getActivity());
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(10);
            mp.rightMargin = UIUtils.dp2px(10);
            mp.topMargin = UIUtils.dp2px(10);
            mp.bottomMargin = UIUtils.dp2px(10);
            tv.setLayoutParams(mp);
            tv.setText(data);
            int r = random.nextInt(210);
            int g = random.nextInt(210);
            int b = random.nextInt(210);
            tv.setBackground(
                    DrawableUtil.getSelector(DrawableUtil.getDrawable(Color.rgb(r, g, b), UIUtils.dp2px(5)),
                            DrawableUtil.getDrawable(Color.WHITE, UIUtils.dp2px(5))));
            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });
            flow.addView(tv);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
