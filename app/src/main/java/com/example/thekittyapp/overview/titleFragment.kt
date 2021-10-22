package com.example.thekittyapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.thekittyapp.R
import com.example.thekittyapp.databinding.FragmentHomeBinding
import com.example.thekittyapp.databinding.FragmentTitleBinding


class titleFragment : Fragment(R.layout.fragment_title) {
    private lateinit var binding: FragmentTitleBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = FragmentTitleBinding.bind(view)


        binding.btnOpen.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_homeFragment)

        )
        binding.btnOpen.setOnClickListener{view : View->
            //this takes the view ,finds enclosing NavHostFragment &
            //returns the navigation controller for that NavHostFragment
            Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_homeFragment)

        }

    }



}