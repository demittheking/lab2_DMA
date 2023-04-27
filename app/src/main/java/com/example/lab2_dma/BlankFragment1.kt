package com.example.lab2_dma
import android.app.usage.UsageEvents
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
class FragmentChangeEvent(val fragmentClass: Class<out Fragment>)
class BlankFragment1 : Fragment() {

    private lateinit var btn_fragment1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fragment1 = view.findViewById(R.id.btn_fragment1)
        btn_fragment1.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment2::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFragmentChange(event: FragmentChangeEvent) {
        val fragmentClass = event.fragmentClass
        val fragment = fragmentClass.newInstance()
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
