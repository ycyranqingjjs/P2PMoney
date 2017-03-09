package cn.itcast.wh.p2pmoney12.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;
import cn.itcast.wh.p2pmoney12.MainActivity;
import cn.itcast.wh.p2pmoney12.R;
import cn.itcast.wh.p2pmoney12.bean.Login;
import cn.itcast.wh.p2pmoney12.common.AppNetConfig;
import cn.itcast.wh.p2pmoney12.common.BaseActivity;
import cn.itcast.wh.p2pmoney12.util.MD5Utils;

/**
 * Created by Administrator on 2015/12/19.
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.log_ed_mob)
    EditText logEdMob;
    @Bind(R.id.about_com)
    RelativeLayout aboutCom;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.log_ed_pad)
    EditText logEdPad;
    @Bind(R.id.log_log_btn)
    Button logLogBtn;

    @Override
    protected void initData() {

    }

    @OnClick(R.id.log_log_btn)
    public void clickLogin(View view) {
        login();
    }

    private void login() {
        String username = logEdMob.getText().toString();
        String password = logEdPad.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            RequestParams params = new RequestParams();
            params.put("username", username);
            params.put("password", MD5Utils.MD5(password));
            client.post(AppNetConfig.LOGIN, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
                    JSONObject jsonObject = JSON.parseObject(content);
                    if (jsonObject.getBoolean("success")) {
                        String data = jsonObject.getString("data");
                        Login login = JSON.parseObject(data, Login.class);
                        //保存到sp中
                        saveLogin(login);
                        //跳转到首页去
                        gotoActivity(MainActivity.class, null);
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                }
            });
        }
    }

    @Override
    protected void initTitle() {
        titleTv.setText("用户登录");
        titleLeft.setVisibility(View.VISIBLE);
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void back(View view) {
        closeCurrent();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}
