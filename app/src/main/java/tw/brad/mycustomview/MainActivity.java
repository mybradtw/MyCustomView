package tw.brad.mycustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MyViewV2 myViewV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewV2 = findViewById(R.id.myview);

    }

    public void clearMyView2(View view) {
        myViewV2.clearLines();
    }
}
