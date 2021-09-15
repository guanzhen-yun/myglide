package com.inke.myglide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.inke.myglide.glide.Glide;
import com.inke.myglide.glide.RequestListener;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };
    private LinearLayout mScrooll_line;
    private String imageUrl = "https://img14.360buyimg.com/pop/jfs/t1/187574/32/4315/176329/60a778f1Eb3aa56c1/ec8e72f7e9b270d8.jpg";
    private String imageUrl1 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F2b.zol-img.com.cn%2Fproduct%2F59%2F225%2FcenWEa3vzaD2.jpg&refer=http%3A%2F%2F2b.zol-img.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281355&t=6b064d1cbd29c2140e6940939bf2c7a8";
    private String imageUrl2 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2Fmn02%2F123120132357%2F201231132357-6.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281317&t=ff51c076a3daf906fa507ca7909a56c2";
    private String imageUrl3 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.huicheimg.com%2Fedpic_360_360%2F4d%2F6f%2Fa5%2F4d6fa5efe63a143a0baa5917046bdbb2.jpg&refer=http%3A%2F%2Fup.huicheimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281317&t=535a0e68a528e7104b27fe7422dee03f";
    private String imageUrl4 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww3.sinaimg.cn%2Fmw690%2F007maN55ly1gt0j3sy2ibj31jk2bc7wh.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281317&t=9eb7b46ff27ec25d3c78cd62005df2b4";
    private String imageUrl5 = "https://img0.baidu.com/it/u=2986156813,960290276&fm=26&fmt=auto&gp=0.jpg";
    private String imageUrl6 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Fb3%2Fed%2F29%2Fb3ed2962fd64aa91407beeef42173fe8.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281317&t=2d6d07e17fe843c24ed37de0da54b8f0";
    private String imageUrl7 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg5.51tietu.net%2Fpic%2F2019-081903%2F0rmh5u4qyyv0rmh5u4qyyv.jpg&refer=http%3A%2F%2Fimg5.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281355&t=d5284a1da59e8e65575f2a7c69c09c38";
    private String imageUrl8 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic38.nipic.com%2F20140215%2F17534965_234431990000_2.jpg&refer=http%3A%2F%2Fpic38.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281355&t=bcd97cd5fd0dca0c5de8c1cc2e4d200a";
    private String imageUrl9 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp0.ssl.qhimgs4.com%2Ft0104968cc6ee506696.jpg%3Fsize%3D800x1139&refer=http%3A%2F%2Fp0.ssl.qhimgs4.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281355&t=6b81c2d4d12553ab4edf79632514d2f7";
    private String imageUrl10 = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2Fmn01%2F041219114146%2F1Z412114146-0.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634281355&t=eb8466706b35bbf6ed98bec7e201fbb8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrooll_line = findViewById(R.id.scrooll_line);
        verifyStoragePermissions(this);

        findViewById(R.id.singe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mScrooll_line.addView(imageView);
                //设置占位图片
                Glide.with(MainActivity.this).load(imageUrl).
                        loading(R.mipmap.ic_launcher).listener(new RequestListener() {

                    @Override
                    public boolean onSuccess(Bitmap bitmap) {
                        Toast.makeText(MainActivity.this, "coming", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean Failure() {
                        return false;
                    }
                }).into(imageView);
            }
        });

        findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 10; i++) {
                    ImageView imageView = new ImageView(MainActivity.this);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    mScrooll_line.addView(imageView);
                    String url = null;
                    switch (i) {
                        case 1:
                            url = imageUrl1;
                            break;
                        case 2:
                            url = imageUrl2;
                            break;
                        case 3:
                            url = imageUrl3;
                            break;
                        case 4:
                            url = imageUrl4;
                            break;
                        case 5:
                            url = imageUrl5;
                            break;
                        case 6:
                            url = imageUrl6;
                            break;
                        case 7:
                            url = imageUrl7;
                            break;
                        case 8:
                            url = imageUrl8;
                            break;
                        case 9:
                            url = imageUrl9;
                            break;
                        case 10:
                            url = imageUrl10;
                            break;
                    }
                    //设置占位图片
                    Glide.with(MainActivity.this).load(url + "").loading(R.mipmap.ic_launcher).into(imageView);
                }
            }
        });

    }

    public static void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                //没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}