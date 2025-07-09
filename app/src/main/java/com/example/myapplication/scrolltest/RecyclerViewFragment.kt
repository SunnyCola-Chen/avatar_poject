package com.example.myapplication.scrolltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerViewFragment : Fragment() {
    var index = 0

    companion object {
        fun newInstance(index: Int): RecyclerViewFragment {
            val fragment = RecyclerViewFragment()
            val args = Bundle()
            args.putInt("index", index)
            fragment.arguments = args
            fragment.index = index
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val linearLayout = LinearLayout(this.context)
        linearLayout.orientation = LinearLayout.VERTICAL
        val text = TextView(this.context).apply {
            this.text = "List ${index}"
        }
        linearLayout.addView(text, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        val recyclerView = RecyclerView(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = object : RecyclerView.Adapter<TextVH>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextVH {
                val tv = TextView(parent.context).apply {
                    setPadding(30, 30, 30, 30)
                    textSize = 20f
                }
                return TextVH(tv)
            }

            override fun onBindViewHolder(holder: TextVH, position: Int) {
                holder.tv.text = "Item $position"
                holder.itemView.setOnClickListener {
                    Snackbar.make(recyclerView.context,  recyclerView, "click ${holder.tv.text}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun getItemCount(): Int = 50
        }
        linearLayout.addView(recyclerView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        return linearLayout
    }

    class TextVH(val tv: TextView) : RecyclerView.ViewHolder(tv)
}
