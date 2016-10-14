package android.lab8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
    int x;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle=getIntent().getExtras();
        x=bundle.getInt("result");
        mTextView= (TextView) findViewById(R.id.textViewScore);
        mTextView.setText(x+"");
    }
    public void onBackPressed() {
        Intent intent=new Intent(ResultActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ResultActivity.this.startActivity(intent);
    }
}
