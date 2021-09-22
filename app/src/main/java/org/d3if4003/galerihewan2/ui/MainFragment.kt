package org.d3if4003.galerihewan2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if4003.galerihewan2.hewan.Hewan
import org.d3if4003.galerihewan2.R
import org.d3if4003.galerihewan2.databinding.FragmentMainBinding
import org.d3if4003.galerihewan2.network.HewanApiService

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: FragmentMainBinding
    private lateinit var myAdapter: MainAdapter

    override fun onCreateView(inflater: LayoutInflater, container:ViewGroup?,
                              savedInstanceState: Bundle?): View?{
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        myAdapter = MainAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)

        })
    }
            fun updateProgress(status: HewanApiService.Companion.HewanApi.ApiStatus) {
            when (status) {
                HewanApiService.Companion.HewanApi.ApiStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                HewanApiService.Companion.HewanApi.ApiStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                }
                HewanApiService.Companion.HewanApi.ApiStatus.FAILED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.networkError.visibility = View.VISIBLE
                }
            }
        }
    }
