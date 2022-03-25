package com.example.android_startservice_example

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestService : Service() {

    override fun onCreate() {
        Log.d("myLog", "TestService.onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("myLog", "TestService.onStartCommand startId:$startId threadName:${Thread.currentThread().name}")

        CoroutineScope(Dispatchers.Default).launch {
            var i = 0
            while (i < 3) {
                try {
                    Thread.sleep(1000)
                    i++
                    Log.d("myLog", "Service ${i}sec passed")
                } catch (e: Exception) {

                }
            }

            stopSelf(startId)
        }

        /**
         * 시스템에 의하여 서비스가 강제로 종료된 경우,
         * START_NOT_STICKY - 강제로 종료된 서비스를 재시작하지 않는다.
         * START_STICKY - intent가 null로 들어오며, 강제로 종료된 서비스를 재시작한다.
         * START_REDELIVER_INTENT - intent가 유지되며, 강제로 종료된 서비스를 재시작한다.
         */
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("myLog", "TestService.onBind")
        return null
    }

    override fun onDestroy() {
        Log.d("myLog", "TestService.onDestroy")
    }

}