package com.example.quoting_mr_west.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.quoting_mr_west.R
import com.example.quoting_mr_west.databinding.FragmentQuoteBinding
import com.example.quoting_mr_west.models.Quote_Model
import com.example.quoting_mr_west.viewmodel.QuoteViewModel
import kotlinx.android.synthetic.main.fragment_quote.*
import kotlinx.android.synthetic.main.fragment_quote.view.*

/**
 * A simple [Fragment] subclass.
 */
class QuoteFragment : Fragment() {

    var quote: Quote_Model? = Quote_Model("What he say??")
    private lateinit var viewModel: QuoteViewModel
    private lateinit var dataBinding: FragmentQuoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_quote, container,
            false
        )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(QuoteViewModel::class.java)
        }

        refresh_layout.setOnRefreshListener {
            quote_text_view.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            refresh_layout.isRefreshing = false
            viewModel.refresh()

        }

        observeQuoteViewModel()
    }

    private fun observeQuoteViewModel() {
        viewModel.quote.observe(this, Observer { quote ->
            quote?.let {
                quote_text_view.visibility = View.VISIBLE
                dataBinding.quote = it
            }

        })

        viewModel.quoteLoaderError.observe(this, Observer { loadingError ->
            loadingError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }

        })

        viewModel.loading.observe(this, Observer { loading ->
            loading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    quote_text_view.visibility = View.GONE
                }
            }

        })

    }

}
