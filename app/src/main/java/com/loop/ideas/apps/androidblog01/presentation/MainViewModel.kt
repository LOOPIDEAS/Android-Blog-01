package com.loop.ideas.apps.androidblog01.presentation

import android.util.Log
import androidx.lifecycle.*
import com.loop.ideas.apps.androidblog01.domain.model.UserDomain
import com.loop.ideas.apps.androidblog01.domain.use_case.GetUserUseCase
import com.loop.ideas.apps.androidblog01.domain.use_case.GetUsersUseCase
import com.loop.ideas.apps.androidblog01.presentation.mapper.UserPresentationMapper
import com.loop.ideas.apps.androidblog01.presentation.model.UserPresentation
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class MainViewModel(private val getUserUseCase: GetUserUseCase,
                    private val getUsersUseCase: GetUsersUseCase,
                    private val mapper: UserPresentationMapper) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _master: MutableLiveData<UserDomain> = MutableLiveData()
    val master: LiveData<UserPresentation> = _master.switchMap {
        liveData(presentationMapperContext) {
            _isSearchingMaster.postValue(false)  // This is hacky, but just for sample purposes.
            emit(mapper.mapDomainUserToPresentation(it))
        }
    }

    val masterName: LiveData<String> = _master.map { "Your master is: ${it.name}" }

    private val _masterList: MutableLiveData<List<UserDomain>> = MutableLiveData()
    val masters: LiveData<List<UserPresentation>> = _masterList.switchMap {
        liveData(presentationMapperContext) {
            _isRetrievingAllMasters.postValue(false)  // This is hacky, but again, just for sample purposes.
            emit(mapper.mapDomainUsersToPresentation(it))
        }
    }

    //region presentation coroutine context
    private val mapperExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Exception!: ${throwable.message}")
    }

    private val presentationMapperContext : CoroutineContext = Dispatchers.IO + mapperExceptionHandler
    //endregion

    //region Utility fields for handling loading animation
    private val _isSearchingMaster : MutableLiveData<Boolean> = MutableLiveData()
    val isSearchingMaster: LiveData<Boolean>
        get() = _isSearchingMaster

    private val _isRetrievingAllMasters : MutableLiveData<Boolean> = MutableLiveData()
    val isRetrievingAllMasters: LiveData<Boolean>
        get() = _isRetrievingAllMasters
    //endregion

    private fun executeGetUserUseCase(userId: Int) {
        viewModelScope.launch {
            _isSearchingMaster.value = true
            _master.value = getUserUseCase.invoke(userId)
        }
    }

    private fun executeGetUsersUseCase() {
        viewModelScope.launch {
            _isRetrievingAllMasters.value = true
            _masterList.value = getUsersUseCase.invoke()
        }
    }

    fun onFindMasterClicked(userId: String?) {
        if (userId.isNullOrEmpty() || userId.isNullOrBlank()) {
            Log.e(TAG, "master number is null or empty.")
            return
        }

        if(userId.toInt() > 5) {
            Log.e(TAG, "only 5 master are allowed.")
            return
        }

        executeGetUserUseCase(userId.toInt() - 1)
    }

    fun onGetAllMasterButtonClicked() {
        // Probably some validations here would be useful.
        executeGetUsersUseCase()
    }
}