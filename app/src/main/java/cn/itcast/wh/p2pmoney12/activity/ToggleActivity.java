package cn.itcast.wh.p2pmoney12.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.common.BaseActivity;
import cn.itcast.wh.p2pmoney12.util.UIUtils;

/**
 * Created by Administrator on 2015/12/20.
 */
public class ToggleActivity extends BaseActivity {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.tg)
    ToggleButton tg;

    @Override
    protected void initData() {
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //密码已经开启
                    UIUtils.Toast("密码已经开启", false);
                } else {
                    UIUtils.Toast("密码已经关闭", false);
                }
            }
        });

    }

    @Override
    protected void initTitle() {
        titleTv.setText("togglebutton");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void back(View view) {
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_toggle;
    }
}
