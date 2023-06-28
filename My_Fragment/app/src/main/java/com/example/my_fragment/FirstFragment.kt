package com.example.my_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match

class FirstFragment : Fragment() {

    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var btnNext:Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etName=view.findViewById(R.id.et_name)
        etEmail=view.findViewById(R.id.et_email)
        btnNext=view.findViewById(R.id.btn_next)

        btnNext.setOnClickListener {
            var name=etName.text.toString().trim()
            var email=etEmail.text.toString().trim()

            var bundel=Bundle()
            bundel.putString("NAME",name)
            bundel.putString("EMAIL",email)

            var fragment=SecondFragment()
            fragment.arguments=bundel
            var manager=requireActivity().supportFragmentManager
            var transaction=manager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

}