package ro.alexmamo.cloudfirestore.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.Dispatchers
import ro.alexmamo.cloudfirestore.data.FirebaseCallback

class ProductsViewModel (
    private val repository: ProductsRepository = ProductsRepository()
): ViewModel() {
    fun getResponseUsingCallback(callback: FirebaseCallback) = repository.getResponseFromFirestoreUsingCallback(callback)

    fun getResponseUsingLiveData() = repository.getResponseFromFirestoreUsingLiveData()

    val responseLiveData = liveData(Dispatchers.IO) {
        emit(repository.getResponseFromFirestoreUsingCoroutines())
    }

    fun getResponseUsingFlow() = liveData(Dispatchers.IO) {
        repository.getResponseFromFirestoreUsingFlow().collect { response ->
            emit(response)
        }
    }
}