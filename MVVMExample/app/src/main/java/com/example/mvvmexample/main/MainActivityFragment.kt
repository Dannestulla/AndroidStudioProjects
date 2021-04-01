package com.example.mvvmexample.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mvvmexample.R

class MainActivityFragment : Fragment() {
    private val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeDogBreed()
    }
    private fun setupClickListeners() {
        //dogbreed_button.
    }
    private fun observeDogBreed() {
        viewModel.dogBreedLiveData.observe(viewLifecycleOwner, Observer { breed -> dogbreed_textview.text = breed})

    }




}