package android.lab8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CreateDB extends Activity {
    private static String dbName="Quiz.db";
    private static String tableName="question";
    private static String createQuery;
    private TextView mTextView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_db);
        mTextView= (TextView) findViewById(R.id.textViewData);
        String str="";
        Intent intent=new Intent(CreateDB.this,MainActivity.class);
        createQuery="create table if not exists "+tableName+" (question varchar(50),op1 varchar(20),op2 varchar(20),answer varchar(20));";
        SQLiteDatabase db=openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);
        db.execSQL(createQuery);
        Cursor data=db.rawQuery("select * from "+tableName,null);
        if(!data.moveToFirst()) {
            db.execSQL("insert into "+tableName+" values ('Which is a primary color?','Gray','Red','Red')");
            db.execSQL("insert into "+tableName+" values ('Which is a mammal?','Dog','Shark','Dog')");
            db.execSQL("insert into "+tableName+" values ('Which is a wonder of the world?','Red fort','Taj Mahal','Taj Mahal')");
            Toast.makeText(CreateDB.this, "Values inserted", Toast.LENGTH_SHORT).show();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            CreateDB.this.startActivity(intent);
        }
        else{


            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            CreateDB.this.startActivity(intent);
        }
        data.close();
        db.close();
    }

}
