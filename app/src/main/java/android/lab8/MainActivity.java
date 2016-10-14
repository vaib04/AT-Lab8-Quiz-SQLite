package android.lab8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG="MainActivity";
    private Button mButton;
    private RadioGroup mRadioGroup;
    private RadioButton rOpt1,rOpt2;
    private TextView mTextView;
    private static String dbName="Quiz.db";
    private static String tableName="question";
    static SQLiteDatabase db=null;
    static Cursor data=null;
    static int score;
    int id;
    String str;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton= (Button) findViewById(R.id.button);
        mRadioGroup= (RadioGroup) findViewById(R.id.rGroup);
        rOpt1= (RadioButton) findViewById(R.id.opOne);
        rOpt2= (RadioButton) findViewById(R.id.opTwo);
        mTextView= (TextView) findViewById(R.id.textView);
        id=0;
        score=0;
        str="";
        db=openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);
        data=db.rawQuery("select * from " + tableName, null);
        data.moveToFirst();
        readData();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=mRadioGroup.getCheckedRadioButtonId();
                Log.d(TAG,"getCheckedRadioButtonId= "+id);
                if(id!=-1) {
                    if(id==rOpt1.getId()) {
                        str= (String) rOpt1.getText();
                        score=check(data,str,score);
                    }
                    else if(id==rOpt2.getId()) {
                        str= (String) rOpt2.getText();
                        score=check(data,str,score);
                    }
                }
                if(data.moveToNext()) {
                    mRadioGroup.clearCheck();
                    readData();
                }
                else {
                    Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                    Log.d(TAG,"intent created to navigate to ResultActivity");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("result",score);
                    Log.d(TAG,"putextra: complete; score="+score);
                    MainActivity.this.startActivity(intent);
                    Log.d(TAG,"ResultActivity started");
                }
            }
        });

        /*do {
            str+=data.getString(3)+"\n";
            mTextView.setText(str);
        }while(data.moveToNext());*/
    }
    private int check(Cursor data,String str,int score) {
        if(str.equals(data.getString(3))) {
            score++;
        }
        else {
            score--;
        }
        return score;
    }
    private void readData() {
        mTextView.setText(data.getString(0));
        rOpt1.setText(data.getString(1));
        rOpt2.setText(data.getString(2));
    }

}
