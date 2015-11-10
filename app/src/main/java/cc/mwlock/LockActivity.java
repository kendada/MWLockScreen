package cc.mwlock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * User: 山野书生(1203596603@qq.com)
 * Date: 2015-11-09
 * Time: 16:00
 * Version 1.0
 *
 * 锁屏显示界面
 */

public class LockActivity extends Activity {

    private Button lock_off_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_layout);

        lock_off_btn = (Button)findViewById(R.id.lock_off_btn);

        lock_off_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LockActivity.this.finish();
            }
        });
    }
}
