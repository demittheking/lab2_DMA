package com.example.lab2_dma
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
class OpenActivity1Event(val data: String)
class Activity2 : AppCompatActivity() {
    private lateinit var button3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            EventBus.getDefault().post(OpenActivity1Event("Hello from Activity2"))
            val intent = Intent(this, Activity1::class.java)
            startActivity(intent)
        }
        EventBus.getDefault().register(this)

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onOpenActivity2Event(event: OpenActivity2Event) {
        val intent = Intent(this, Activity2::class.java)
        intent.putExtra("data", event.data)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
