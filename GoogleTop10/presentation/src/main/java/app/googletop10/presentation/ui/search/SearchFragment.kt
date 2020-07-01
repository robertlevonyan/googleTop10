package app.googletop10.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.googletop10.presentation.R
import app.googletop10.presentation.databinding.FragmentSearchBinding
import app.googletop10.presentation.util.dataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    private val binding: FragmentSearchBinding by dataBinding(R.layout.fragment_search)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.onSearchError.observe(viewLifecycleOwner, Observer {
            context?.run { Toast.makeText(this, R.string.error_search, Toast.LENGTH_SHORT).show() }
        })
    }

}