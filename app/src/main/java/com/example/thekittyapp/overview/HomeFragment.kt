package com.example.thekittyapp.overview

import DataAdapter
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thekittyapp.R
import com.example.thekittyapp.api.BreedResponse
import com.example.thekittyapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding

    private lateinit var progerssProgressDialog: ProgressDialog

    private lateinit var recyclerView: RecyclerView

    private var myAdapter: DataAdapter? = null


    companion object {
        // the server url endpoint
        const val serverUrl = "https://api.thecatapi.com/v1/"
        const val apiKey = "a60e81d3-735f-4bdd-8adb-cd618f0ce4cb"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //this.binding = FragmentHomeBinding.inflate(layoutInflater)
        this.binding = FragmentHomeBinding.bind(view)
        this.binding.recyclerView
        showProgressDialog()

        // setting up viewModel
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        viewModel.getSomeCats()
        // any change observed will run the corresponding method
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { onError(it) })
        // observing the stuff we are interested about.
        viewModel.randomCats.observe(viewLifecycleOwner, Observer {
            onResult(it)
            dismissProgressDialog()
        })



        this.binding.refreshButton.setOnClickListener {
            viewModel.getSomeCats()
        }
    }

    private fun dismissProgressDialog() {
        progerssProgressDialog.dismiss()
    }

    private fun showProgressDialog() {
        progerssProgressDialog = ProgressDialog(requireActivity())
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
    }

    private fun onResult(catsRetrieved: List<BreedResponse>) {
        Toast.makeText(
            this@HomeFragment.requireActivity(),
            "Got ${catsRetrieved.size} cats",
            Toast.LENGTH_SHORT
        ).show()
        recyclerView = findViewById(R.id.recyclerView)
        myAdapter = DataAdapter(catsRetrieved)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)


    }

    private fun findViewById(recyclerView: Int): RecyclerView {
        return this.binding.recyclerView
    }


    private fun onError(error: String) {
        Log.e("error", "$error")
        // a simple toast in case things went wrong
        error.let {
            if (!it.isBlank()) {
                Toast.makeText(this@HomeFragment.requireActivity(), error, Toast.LENGTH_SHORT)
                    .show()
            }

        }
        progerssProgressDialog.dismiss()


    }
    private fun showFailedResponse() {

    }


}