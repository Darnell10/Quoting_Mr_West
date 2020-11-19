package com.example.quoting_mr_west.view


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.quoting_mr_west.R
import com.example.quoting_mr_west.databinding.FragmentQuoteBinding
import com.example.quoting_mr_west.databinding.SendSmsDialogBinding
import com.example.quoting_mr_west.models.Quote_Model
import com.example.quoting_mr_west.models.SmsInfo
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
    private var currentQuote: Quote_Model? = null

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
            currentQuote = quote
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
               (activity as MainActivity).checkSmsPermissions()
            }
            R.id.action_share -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onPermissionResult(permissionGranted: Boolean) {
        if (sendTextStarted && permissionGranted) {
            context?.let {
                val smsInfo = SmsInfo("", "", "${currentQuote?.quote} + @string/credit_string")

                val dialogBinding = DataBindingUtil.inflate<SendSmsDialogBinding>(
                    LayoutInflater.from(it),
                    R.layout.send_sms_dialog,
                    null,
                    false
                )

                AlertDialog.Builder(it)
                    .setView(dialogBinding.root)
                    .setPositiveButton("Send Quote") { dialog, which ->
                        if (!dialogBinding.smsInfo?.text.isNullOrEmpty()) {
                            smsInfo.to = dialogBinding.smsInfo?.text.toString()
                            sendSms(smsInfo)
                        }
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                    }.show()

                dialogBinding.smsInfo = smsInfo
            }
        }
    }

    private fun sendSms(smsInfo: SmsInfo) {

    }

}
