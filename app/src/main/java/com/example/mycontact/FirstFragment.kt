package com.example.mycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mycontact.databinding.FragmentFirstBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FirstFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentFirstBinding

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
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_addFragment)
        }

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}