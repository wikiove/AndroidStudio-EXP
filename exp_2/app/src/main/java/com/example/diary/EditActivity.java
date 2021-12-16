package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import java.text.SimpleDateFormat;
import java.util.Date;

/*
日记编辑页面
可以更改的元素：title,author,context
 */
public class EditActivity extends BaseActivity {

    private EditText editText;//编辑的笔记
    private EditText title;//日记标题
    private EditText author;//日记作者
    //需要更新的旧数据
    private String old_content = "";
    private String old_title = "";
    private String old_author = "";
    private int old_tag = 1;

    private long id = 0;
    private int openMode = 0;
    private int tag = 1;

    public Intent intent = new Intent();
    private Boolean tagChange = false;//默认笔记标签没有更改
    private Toolbar noteToolbar;

    /**
     * 引入menu菜单栏
     *
     * @param menu
     * @return
     */

    //引入edit菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        //对象实例化
        editText = findViewById(R.id.editText1);
        title = findViewById(R.id.edit_title);
        author = findViewById(R.id.author);
        noteToolbar = findViewById(R.id.noteToolbar);

        //设置Action Bar，自定义Toolbar
        setSupportActionBar(noteToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置toolber取代action bar

        //设置Navigation返回键的事件监听，与系统的返回键功能相同
        noteToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoSetMessage(intent);//自动更新笔记
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Intent getIntent = getIntent();
        //定义意图
        openMode = getIntent.getIntExtra("mode", 0);

        Log.d("System=====", "openMode:" + openMode);
        if (openMode == 3) {
            //打开已存在的note，将内容写入到已编辑的笔记中，实现继续编辑
            id = getIntent.getLongExtra("id", 0);
            old_content = getIntent.getStringExtra("content");
            old_title = getIntent.getStringExtra("title");
            old_author = getIntent.getStringExtra("author");
            old_tag = getIntent.getIntExtra("tag", 1);
            //填充内容
            title.setText(old_title);
            author.setText(old_author);
            editText.setText(old_content);
        }
    }

    /**
     * 设置菜单栏按钮监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.delete:
                new AlertDialog.Builder(EditActivity.this)
                        .setMessage("您确定要删除吗？")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (openMode == 4) {//如果是新增笔记，则不创建
                                    intent.putExtra("mode", -1);
                                    setResult(RESULT_OK, intent);
                                } else {//如果是修改笔记，则删除
                                    intent.putExtra("mode", 2);
                                    intent.putExtra("id", id);
                                    setResult(RESULT_OK, intent);
                                }
                                finish();
                            }
                        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭窗口
                    }
                }).create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 系统按钮监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            autoSetMessage(intent);
            //按下返回键，将页面的文本获取，并返回
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 判断是新增笔记还是修改笔记,mode值是3为修改，为4是新增
     */
    public void autoSetMessage(Intent intent) {
        if (openMode == 4) {
            Log.d("input", "input11111");
            //判断日记本内容是否为空，若为空，则不新增笔记
            if (editText.getText().toString().length() == 0) {
                intent.putExtra("mode", -1);
            } else {
                intent.putExtra("mode", 0);
                intent.putExtra("content", editText.getText().toString());
                intent.putExtra("author", author.getText().toString());
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("time", dateToStr());
                intent.putExtra("tag", tag);
            }
        } else {
            //判断笔记是否被修改，或者标签是否更换，否则不更新笔记
            if (editText.getText().toString().equals(old_content)
            && title.getText().toString().equals(old_title)
            && author.getText().toString().equals(old_author)
                    && !tagChange)
            {
                intent.putExtra("mode", -1);
            } else {
                intent.putExtra("mode", 1);
                intent.putExtra("content", editText.getText().toString());
                intent.putExtra("author", author.getText().toString());
                intent.putExtra("title", title.getText().toString());
                intent.putExtra("time", dateToStr());
                intent.putExtra("id", id);
                intent.putExtra("tag", tag);
            }
        }
    }

    /**
     * 转换时间格式
     *
     * @return
     */
    public String dateToStr() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}