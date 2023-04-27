package com.example.lab2_dma
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import com.example.lab2_dma.FragmentChangeEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class BlankFragment2 : Fragment() {
    private lateinit var btn_fragment1: Button
    private lateinit var btn_fragment2: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fragment1 = view.findViewById(R.id.btn_fragment1)
        btn_fragment2 = view.findViewById(R.id.btn_fragment2)
        btn_fragment2.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment3::class.java))
        }
        btn_fragment1.setOnClickListener {
            EventBus.getDefault().post(FragmentChangeEvent(BlankFragment1::class.java))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
