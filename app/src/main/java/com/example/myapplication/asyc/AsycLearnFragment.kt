package com.example.myapplication.asyc

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class AsycLearnFragment: Fragment() {

    companion object {
        fun newInstance(): AsycLearnFragment {
            return AsycLearnFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_dashboardview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.title)
        title.text = "AsycLearnFragment"
        // 主线程handler
        var handler = Handler(Looper.getMainLooper())
        handler.post {  }

        //子线程handler
        val handlerThread = HandlerThread("Work").apply { start() }
        handler = Handler(handlerThread.looper)
        handler.post {  }
    }

}