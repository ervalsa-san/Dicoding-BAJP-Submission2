package com.ervalsa.tmdb.ui.home.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ervalsa.tmdb.data.DataEntity
import com.ervalsa.tmdb.databinding.FragmentTvshowBinding
import com.ervalsa.tmdb.ui.detail.DetailActivity
import com.ervalsa.tmdb.ui.home.HomeViewModel
import com.ervalsa.tmdb.ui.home.content.ContentAdapter
import com.ervalsa.tmdb.ui.home.content.ContentCallback
import com.ervalsa.tmdb.utils.Helper.TYPE_TVSHOW
import com.ervalsa.tmdb.viewmodel.ViewModelFactory


class TvShowFragment : Fragment(), ContentCallback {

    private lateinit var fragmentTvshowBinding: FragmentTvshowBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentTvshowBinding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return fragmentTvshowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val factory = ViewModelFactory.getInstance()
        activity?.let {
            viewModel = ViewModelProvider(it, factory)[HomeViewModel::class.java]
        }

        viewModel.getListTvShowAiringToday().observe(viewLifecycleOwner, Observer { listTvShow ->
            fragmentTvshowBinding.rvTvShow.adapter.let { adapter ->
                when (adapter) {
                    is ContentAdapter -> adapter.setData(listTvShow)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        fragmentTvshowBinding.rvTvShow.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = ContentAdapter(this@TvShowFragment)
        }
    }

    override fun onItemClicked(data: DataEntity) {
        startActivity(Intent(context, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_DATA, data.Id)
            .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW))
    }
}