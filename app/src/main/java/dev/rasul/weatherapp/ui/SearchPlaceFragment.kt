package dev.rasul.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.rasul.weatherapp.PreferenceUtil
import dev.rasul.weatherapp.R
import dev.rasul.weatherapp.adapter.SearchPlaceAdapter
import dev.rasul.weatherapp.databinding.FragmentSearchPlaceBinding
import dev.rasul.weatherapp.extensions.removeFocus
import dev.rasul.weatherapp.viewModel.SearchPlaceViewModel
import dev.rasul.weatherapp.viewModel.SearchPlaceViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class SearchPlaceFragment : Fragment() {

    lateinit var binding: FragmentSearchPlaceBinding

    private val mAdapter: SearchPlaceAdapter = SearchPlaceAdapter { newPlace ->
        PreferenceUtil.setSelectedPlace(requireContext(), newPlace)
        parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }

    @Inject
    lateinit var searchPlaceViewModelFactory: SearchPlaceViewModelFactory

    private lateinit var mViewModel: SearchPlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {


        binding.resultsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        binding.edtSearch.setOnEditorActionListener(OnEditorActionListener { tv, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mViewModel.getPlaces(tv.text.toString())
                binding.edtSearch.removeFocus()
                return@OnEditorActionListener true
            }
            false
        })
        if (PreferenceUtil.getSelectedPlace(requireContext()) == null) {
            binding.btnBack.visibility = View.INVISIBLE
        }
        binding.btnBack.setOnClickListener {
            if (PreferenceUtil.getSelectedPlace(requireContext()) == null) {
                parentFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
                    .commit()
            } else {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun initViewModel() {
        mViewModel =
            ViewModelProvider(this, searchPlaceViewModelFactory)[SearchPlaceViewModel::class.java]
        mViewModel.errorLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
        mViewModel.placesLiveData.observe(viewLifecycleOwner, { places ->
            mAdapter.places = places
        })

        mViewModel.loadingLiveData.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
    }
}