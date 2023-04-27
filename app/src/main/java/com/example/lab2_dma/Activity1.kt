package com.example.lab2_dma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.greenrobot.eventbus.EventBus
import com.example.lab2_dma.OpenActivity2Event
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class OpenActivity2Event(val data: String)
class Activity1 : AppCompatActivity() {
    private lateinit var button2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            EventBus.getDefault().post(OpenActivity2Event("Hello from Activity1"))
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        EventBus.getDefault().register(this)

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onOpenActivity1Event(event: OpenActivity1Event) {
        val intent = Intent(this, Activity1::class.java)
        intent.putExtra("data", event.data)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}