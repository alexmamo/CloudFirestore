package ro.alexmamo.cloudfirestore.products

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.alexmamo.cloudfirestore.data.FirebaseCallback
import ro.alexmamo.cloudfirestore.data.Product
import ro.alexmamo.cloudfirestore.data.Response
import ro.alexmamo.cloudfirestore.utils.Constants.PRODUCTS_REF

class ProductsRepository(
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val productRef: CollectionReference = rootRef.collection(PRODUCTS_REF)
) {
    fun getResponseFromFirestoreUsingCallback(callback: FirebaseCallback) {
        productRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.products = result.documents.mapNotNull { snapShot ->
                        snapShot.toObject(Product::class.java)
                    }
                }
            } else {
                response.exception = task.exception
            }
            callback.onResponse(response)
        }
    }

    fun getResponseFromFirestoreUsingLiveData() : MutableLiveData<Response> {
        val mutableLiveData = MutableLiveData<Response>()
        productRef.get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.products = result.documents.mapNotNull { snapShot ->
                        snapShot.toObject(Product::class.java)
                    }
                }
            } else {
                response.exception = task.exception
            }
            mutableLiveData.value = response
        }
        return mutableLiveData
    }

    suspend fun getResponseFromFirestoreUsingCoroutines(): Response {
        val response = Response()
        try {
            response.products = productRef.get().await().documents.mapNotNull { snapShot ->
                snapShot.toObject(Product::class.java)
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }

    fun getResponseFromFirestoreUsingFlow() = flow {
        val response = Response()
        try {
            response.products = productRef.get().await().documents.mapNotNull { snapShot ->
                snapShot.toObject(Product::class.java)
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        emit(response)
    }
}