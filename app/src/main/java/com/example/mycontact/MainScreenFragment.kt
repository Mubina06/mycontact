package com.example.mycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.mycontact.Adapter.Adapter
import com.example.mycontact.DBHelper.DBHelper
import com.example.mycontact.Data.for_item
import com.example.mycontact.databinding.FragmentMainScreenBinding
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainScreenFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var list = mutableListOf<for_item>()
    lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        val binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val db = DBHelper(requireContext())

        list = db.getContacts()

            adapter = Adapter(list, object : Adapter.ContactInterface{
                override fun onClick(contact: for_item) {
                    val bundle = bundleOf()
                    bundle.putSerializable("contact",contact)
                    findNavController().navigate(R.id.action_mainScreenFragment_to_contactInfoFragment,bundle)
                }

            })

            binding.contactRv.adapter = adapter

        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_addFragment)
        }

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText, adapter)
                return true
            }

        })

        return binding.root
    }
    private fun filterList(query: String?, adapter: Adapter){
        if(query != null){
            val filteredList = ArrayList<for_item>()
            for(i in list){
                if(i.name.toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){

            }else{
                adapter.FilteredList(filteredList)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}