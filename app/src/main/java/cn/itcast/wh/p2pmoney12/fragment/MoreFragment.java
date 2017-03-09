package cn.itcast.wh.p2pmoney12.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.common.BaseFragment;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MoreFragment extends BaseFragment {


    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
//        tv.setFocusable(true);
//        tv.setFocusableInTouchMode(true);
//        tv.requestFocus();

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }
}
