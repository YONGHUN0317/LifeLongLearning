/*
package com.src.presentation.views.splash_location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.src.domain.repository.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SplashLocationViewModel @Inject constructor(private val placesRepository: PlacesRepository) : ViewModel() {

    private val _searchResults = MutableLiveData<List<String>>()
    val searchResults: LiveData<List<String>> get() = _searchResults

    fun searchPlaces(query: String) {
        placesRepository.searchPlaces(query, "ko", "KR") // language and region bias
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ results ->
                _searchResults.value = results
            }, { error ->
                // Handle error
            })
        // Dispose or add to a CompositeDisposable, cleared when ViewModel is cleared
    }
}
*/
