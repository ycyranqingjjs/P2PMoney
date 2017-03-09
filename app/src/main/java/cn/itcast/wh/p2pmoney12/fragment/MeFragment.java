package cn.itcast.wh.p2pmoney12.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.Bind;
import butterknife.OnClick;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.activity.BarChartActivity;
import cn.itcast.wh.p2pmoney12.activity.ChongzhiActivity;
import cn.itcast.wh.p2pmoney12.activity.LineChartActivity;
import cn.itcast.wh.p2pmoney12.activity.LoginActivity;
import cn.itcast.wh.p2pmoney12.activity.PieChartActivity2;
import cn.itcast.wh.p2pmoney12.activity.TiXianActivity;
import cn.itcast.wh.p2pmoney12.activity.ToggleActivity;
import cn.itcast.wh.p2pmoney12.activity.UserInfoActivity;
import cn.itcast.wh.p2pmoney12.bean.Login;
import cn.itcast.wh.p2pmoney12.common.BaseActivity;
import cn.itcast.wh.p2pmoney12.common.BaseFragment;
import cn.itcast.wh.p2pmoney12.util.BitMapUtil;
import cn.itcast.wh.p2pmoney12.util.UIUtils;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MeFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    @Bind(R.id.icon_time)
    RelativeLayout iconTime;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @Bind(R.id.chongzhi)
    ImageView chongzhi;
    @Bind(R.id.tixian)
    ImageView tixian;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichang)
    TextView llZichang;
    @Bind(R.id.ll_zhanquan)
    TextView llZhanquan;

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
        isLogin();
    }

    private void isLogin() {
        SharedPreferences sp = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String uf_acc = sp.getString("UF_ACC", "");
        if (TextUtils.isEmpty(uf_acc)) {
            //未登录
            showLoginDialog();
        } else {
            //已登录--处理信息
            doUser();
        }
    }

    private void doUser() {
        Login login = ((BaseActivity) getActivity()).getLogin();
        textView11.setText(login.UF_ACC);
        Picasso.with(getActivity()).load(login.UF_AVATAR_URL).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap zoom = BitMapUtil.zoom(source, UIUtils.dp2px(62), UIUtils.dp2px(62));
                Bitmap circleBitMap = BitMapUtil.circleBitMap(zoom);
                //1:transform当中处理完图片之后，需要调用recylce方法回收
                source.recycle();
                return circleBitMap;
            }

            @Override
            public String key() {
                //2:重写key方法的返回值，不能是null
                return "";
            }
        }).into(imageView1);
    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("登录");
        builder.setMessage("必须先登录...go...");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((BaseActivity) getActivity()).gotoActivity(LoginActivity.class, null);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    protected void initTitle() {
        titleTv.setText("我的资产");
        titleLeft.setVisibility(View.INVISIBLE);
        titleRight.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @OnClick(R.id.title_right)
    public void clickUserSetting(View view) {
        ((BaseActivity) getActivity()).gotoActivity(UserInfoActivity.class, null);
    }

    @OnClick(R.id.chongzhi)
    public void cz(View view) {
        ((BaseActivity) getActivity()).gotoActivity(ChongzhiActivity.class, null);
    }

    @OnClick(R.id.tixian)
    public void tx(View view) {
        ((BaseActivity) getActivity()).gotoActivity(TiXianActivity.class, null);
    }

    @OnClick(R.id.ll_touzi)
    public void line(View view) {
        ((BaseActivity) getActivity()).gotoActivity(LineChartActivity.class, null);
    }

    @OnClick(R.id.ll_touzi_zhiguan)
    public void bar(View view) {
        ((BaseActivity) getActivity()).gotoActivity(BarChartActivity.class, null);
    }

    @OnClick(R.id.ll_zichang)
    public void pie(View view) {
        ((BaseActivity) getActivity()).gotoActivity(PieChartActivity2.class, null);
    }

    @OnClick(R.id.ll_zhanquan)
    public void toggle(View view) {
        ((BaseActivity) getActivity()).gotoActivity(ToggleActivity.class, null);
    }
}
