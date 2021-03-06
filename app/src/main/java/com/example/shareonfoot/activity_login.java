package com.example.shareonfoot;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.shareonfoot.home.activity_home;
import com.example.shareonfoot.signup.activity_signup;
import com.example.shareonfoot.signup.activity_signup_next;

public class activity_login extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    int count = 0;

    EditText userId, userPwd;
    Button loginBtn, joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        BtnOnClickListener onClickListener = new BtnOnClickListener();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_login);
        Button btnSignup = findViewById(R.id.bt_signup);
        btnSignup.setOnClickListener(onClickListener);
        Button btnLogin = findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(onClickListener);

        userId = (EditText) findViewById(R.id.et_ID);
        userPwd = (EditText) findViewById(R.id.et_Password);
        loginBtn = (Button) findViewById(R.id.bt_login);
        joinBtn = (Button) findViewById(R.id.bt_join);
    }





    public class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.bt_login: // 로그인 버튼 눌렀을 경우
                    String loginid = userId.getText().toString();
                    String loginpwd = userPwd.getText().toString();
                    SharedPreferences sharedPreferences=getSharedPreferences("pref",0);
                    if (loginid.length() == 0||loginpwd.length()==0) {
                        Toast.makeText(activity_login.this, "회원정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    String userID = sharedPreferences.getString(loginid,"");
                    String userPW = sharedPreferences.getString(loginid+"PW","");
                    if(userID.equals("")){
                        Toast.makeText(activity_login.this,"회원정보가 없어요.",Toast.LENGTH_SHORT).show();
                    }else if(!userID.equals(loginid)||!userPW.equals(loginpwd)){
                        Toast.makeText(activity_login.this,"회원정보를 확인해주세요.",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        SharedPreferences pref= getSharedPreferences("pref",0);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("login",loginid);
                        editor.commit();
                        Intent intent = new Intent(activity_login.this, activity_home.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
                case R.id.bt_signup: // 회원가입
                    Intent intent = new Intent(activity_login.this, activity_signup.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}