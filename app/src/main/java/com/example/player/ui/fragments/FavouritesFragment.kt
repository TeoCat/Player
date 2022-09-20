package com.example.player.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.player.R
import com.example.player.adapters.ChannelsAdapter
import com.example.player.ui.ChannelsViewModel
import com.example.player.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    lateinit var viewModel: ChannelsViewModel
    lateinit var channelsAdapter: ChannelsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        channelsAdapter.setOnItemClickListener {
                viewModel.deleteChannel(it)
                Snackbar.make(view, "Channel unsaved successfully", Snackbar.LENGTH_SHORT).show()
        }

        viewModel.getSavedChannels().observe(viewLifecycleOwner, Observer { channel ->
            channelsAdapter.differ.submitList(channel)
        })
    }

    private fun setupRecyclerView() {
        channelsAdapter = ChannelsAdapter()
        favouritesRv.apply {
            adapter = channelsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}