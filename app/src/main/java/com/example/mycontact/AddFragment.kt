package com.example.mycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mycontact.DBHelper.DBHelper
import com.example.mycontact.Data.for_item
import com.example.mycontact.databinding.FragmentAddBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        var dbHelper = DBHelper(requireContext())

        binding.add.setOnClickListener {

            if (binding.userName.text == null){
                Toast.makeText(requireContext(), "You have to fill all inputs", Toast.LENGTH_SHORT).show()
            } else{
                findNavController().navigate(R.id.action_addFragment_to_mainScreenFragment)
            }

            var name:String = binding.userName.text.toString()
            var phone:String = binding.phoneN.text.toString()
            var contact = for_item(name = name, phone_number = phone)
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_firstFragment)
        }
        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}