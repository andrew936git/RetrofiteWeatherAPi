package com.example.retrofiteweatherapi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.textView)
        val cityET = view.findViewById<EditText>(R.id.cityET)
        val saveBT = view.findViewById<Button>(R.id.saveBT)
        val viewPagerItem = arguments?.getSerializable("picture") as Picture
        textView.text = viewPagerItem.text
        imageView.setImageResource(viewPagerItem.image)

        val check = viewPagerItem.isCheck

        if (!check){
            cityET.visibility = View.VISIBLE
            saveBT.visibility = View.VISIBLE

            saveBT.setOnClickListener {
                val city = cityET.text.toString()
                val intent = Intent(activity, SecondActivity::class.java)
                intent.putExtra("city", city)
                startActivity(intent)
            }
        }
    }
}
