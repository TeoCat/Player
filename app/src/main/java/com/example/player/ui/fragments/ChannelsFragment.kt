package com.example.player.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.player.R
import com.example.player.adapters.ChannelsAdapter
import com.example.player.ui.ChannelsViewModel
import com.example.player.ui.MainActivity
import com.example.player.util.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_channels.*

class ChannelsFragment : Fragment(R.layout.fragment_channels) {

    lateinit var viewModel: ChannelsViewModel
    lateinit var channelsAdapter: ChannelsAdapter

    val TAG = "ChannelsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        channelsAdapter.setOnItemClickListener {

            if (it.fav) {
                viewModel.deleteChannel(it)
                Snackbar.make(view, "Channel unsaved successfully", Snackbar.LENGTH_SHORT).show()
                it.fav = false
            } else {
                it.fav = true
                viewModel.saveChannel(it)
                Snackbar.make(view, "Channel saved successfully", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.allChannels.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        channelsAdapter.differ.submitList(newsResponse.channels)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
            }
        })
    }


    override fun onResume() {
        Log.e(TAG, "Обновление")
        super.onResume()
        viewModel.getAllChannels()
    }

    private fun setupRecyclerView() {
        channelsAdapter = ChannelsAdapter()
        channelsRv.apply {
            adapter = channelsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}