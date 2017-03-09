package cn.itcast.wh.p2pmoney12.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.common.BaseActivity;
import cn.itcast.wh.p2pmoney12.util.UIUtils;

/**
 * Created by Administrator on 2015/12/20.
 * <p/>
 * p2p金融项目当中的提现业务:
 * 并不是即时到账，业务流程：用户发送一个提现请求---->经过这个公司审核该请求--->审核通过--->24小时或48小时内将提现的钱款打入收款账户银行卡
 */
public class TiXianActivity extends BaseActivity {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.account_zhifubao)
    TextView accountZhifubao;
    @Bind(R.id.select_bank)
    RelativeLayout selectBank;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.input_money)
    EditText inputMoney;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initData() {


    }

    @Override
    protected void initTitle() {
        titleTv.setText("提现");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void back(View view) {
        closeCurrent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tixian;
    }

    @OnClick(R.id.btn_tixian)
    public void clicktx(View view) {
        Toast.makeText(this, "你的提现请求已被成功受理...审核通过后，24小时内，你的钱自然会到账...", Toast.LENGTH_SHORT).show();
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeCurrent();
            }
        }, 2000);
    }
}
