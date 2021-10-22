package com.example.thekittyapp.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thekittyapp.BuildConfig
import com.example.thekittyapp.MainActivity
import com.example.thekittyapp.api.BreedResponse
import com.example.thekittyapp.repository.KittyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel: ViewModel() {
    private val compositeDisposableOnDestroy = CompositeDisposable()
    private var latestCatCall: Disposable? = null


    // the list the will be observed by the activity
    val randomCats = MutableLiveData<List<BreedResponse>>()

    // the error message observed
    val errorMessage = MutableLiveData<String>()

    // the API call
    fun getSomeCats() {
        Log.d("TAG", "getSomeCats Called")
        // initialising the repository class with the necessary information
        val kittyRepository = KittyRepository(HomeFragment.serverUrl, BuildConfig.DEBUG, HomeFragment.apiKey)

        // stopping the last call if it's already running (optional)
        latestCatCall?.dispose()


        // asking for 20 cats.
        latestCatCall = kittyRepository.getCats(20, null, null).subscribeOn(Schedulers.io())
            .doOnSubscribe {
                compositeDisposableOnDestroy.add(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                when {
                    result.hasError() -> result.errorMessage?.let {
                        // anyone who observes this will be notified of the change automatically
                        errorMessage.postValue("Error getting cats $it")
                    }
                        ?: run {
                            // anyone who observes this will be notified of the change automatically
                            errorMessage.postValue("Null error :(")
                        }
                    result.hasKitty() -> result.breedResponse?.let {
                        // anyone who observes this will be notified of the change automatically
                        randomCats.postValue(it)
                        // clearing the error if it existed
                        errorMessage.postValue("")
                    }
                        ?: run {
                            // anyone who observes this will be notified of the change automatically
                            errorMessage.postValue("Null list of cats :(")
                        }
                    else -> {
                        // anyone who observes this will be notified of the change automatically
                        errorMessage.postValue("No cats available :(")
                    }
                }
            }
    }
   
    // clearing the collection of disposables = no memory leaks no matter what
    override fun onCleared() {
        compositeDisposableOnDestroy.clear()
        Log.d("TAG", "Clearing ViewModel")
        super.onCleared()
    }
}