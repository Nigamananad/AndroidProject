package com.example.my_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match

class SecondFragment : Fragment() {

    lateinit var tvView:TextView
    lateinit var etAddress:EditText
    lateinit var btnNext1:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvView=view.findViewById(R.id.tv_view)
        etAddress=view.findViewById(R.id.et_address)
        btnNext1=view.findViewById(R.id.btn_next1)

        var name=arguments?.getString("NAME")
        var email=arguments?.getString("EMAIL")

        btnNext1.setOnClickListener {
            var address=etAddress.text.toString().trim()
            var tvview=tvView.text.toString().trim()

            var bundel=Bundle()
            bundel.putString("ADDRESS",address)
            bundel.putString("TVVIEW",tvview)

            var fragment=ThirdFragment()
            fragment.arguments=bundel
            var manager=requireActivity().supportFragmentManager
            var transaction=manager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        tvView.text="""
            name:$name
            email:$email
        """.trimIndent()


    }

}