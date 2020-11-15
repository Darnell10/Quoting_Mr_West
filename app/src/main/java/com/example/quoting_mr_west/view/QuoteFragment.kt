package com.example.quoting_mr_west.view


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
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
    private var sendTextStarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_text -> {
                sendTextStarted = true
              //  (activity as MainActivity).checkTextPermission()
            }
            R.id.action_share -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onPermissionResult(permissionGranted :Boolean){

    }

}
