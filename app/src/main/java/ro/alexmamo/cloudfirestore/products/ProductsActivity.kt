package ro.alexmamo.cloudfirestore.products

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ro.alexmamo.cloudfirestore.R
import ro.alexmamo.cloudfirestore.data.FirebaseCallback
import ro.alexmamo.cloudfirestore.data.Response
import ro.alexmamo.cloudfirestore.utils.Constants.TAG

class ProductsActivity : AppCompatActivity() {
    private lateinit var viewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        getResponseUsingCallback()
        getResponseUsingLiveData()
        getResponseUsingCoroutines()
        getResponseUsingFlow()
    }

    private fun getResponseUsingCallback() {
        viewModel.getResponseUsingCallback(object : FirebaseCallback {
            override fun onResponse(response: Response) {
                print(response)
            }
        })
    }

    private fun getResponseUsingLiveData() {
        viewModel.getResponseUsingLiveData().observe(this, {
            print(it)
        })
    }

    private fun getResponseUsingCoroutines() {
        viewModel.responseLiveData.observe(this, {
            print(it)
        })
    }

    private fun getResponseUsingFlow() {
        viewModel.getResponseUsingFlow().observe(this, {
            print(it)
        })
    }

    private fun print(response: Response) {
        response.products?.let { products ->
            products.forEach{ product ->
                product.name?.let {
                    Log.i(TAG, it)
                }
            }
        }

        response.exception?.message?.let {
            Log.e(TAG, it)
        }
    }
}