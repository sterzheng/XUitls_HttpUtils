package com.sterzheng.xuitls_httputils1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * HttpUtils的GET请求
 *      1.实例化HttpUtils
 *      HttpUtils httpUtils = new HttpUtils();
 *      2.发送请求
 *      httpUtils.send();
 */
public class MainActivity extends AppCompatActivity {

    private HttpHandler<String> reqManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestClick(View view) {
        //1.实例化HttpUtils
        /**
         * 参数一：请求超时时间
         * 参数二：userAgent{用户代理}
         */
        HttpUtils httpUtils = new HttpUtils(3000);


        //2.发送请求
        //同步请求
        //httpUtils.sendSync()
        /**
         * 参数一：请求方式
         * 参数二：url地址
         * 参数三：请求回调接口
         *         RequestCallback<T>(){
         *             onSuccess(ResponseInfo responseInfo);
         *             onFailure(HttpException exception,String msg);
         *         }
         *
         * 注意：T一般可以设置为
         *        T:  String,byte[],ResponseInfo,File
         *
         */
        reqManager = httpUtils.send(
                HttpRequest.HttpMethod.GET,
                String.format(Uri.URL_PIC,1),
                new RequestCallBack<String>() {
                    /**
                     * 成功的回调
                     * @param responseInfo :响应值 :对数据的封装
                     *                     有效数据的获取
                     *                     responseInfo.result;
                     */
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.i("info", responseInfo.result);
                    }

                    /**
                     * 失败的回调
                     * @param error :异常
                     * @param msg :错误信息
                     */
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Log.i("info",msg);
                    }
                });


    }
    //界面销毁时，取消请求
    @Override
    protected void onDestroy() {
        reqManager.cancel();
        super.onDestroy();
    }
}
