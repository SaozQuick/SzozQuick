package org.saozquick.sample;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.saozquick.R;
import org.saozquick.commom.BaseActivity;
import org.saozquick.commom.BaseDto;
import org.saozquick.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private String userName;
    private String password;

    @Override
    protected ViewModel initViewModel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        return loginViewModel;
    }

    @Override
    protected void initOnCreate() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录操作
                userName = binding.username.getText().toString().trim();
                password = binding.password.getText().toString().trim();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "随便输入点东西吧", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginViewModel.doLogin(userName, password);
                //结果监听
                loginViewModel.getLoginResult().observe(LoginActivity.this, new Observer<BaseDto<LoginDto>>() {
                    @Override
                    public void onChanged(BaseDto<LoginDto> result) {
                        if (result.getErrorCode() == 0) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "" + result.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                        binding.result.setText(result.toString());
                    }
                });
            }
        });
    }
}
