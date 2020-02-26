package com.task.demo.configuration

import com.task.demo.R
import com.task.demo.data.model.WrapperPrizes
import retrofit2.Response


class APIHelper : BaseAPIHelper() {

    private fun getString(stringId: Int): String {
        return App.getInstance().getString(stringId)
    }

    private fun showGeneralizedError(): String {
        return getString(R.string.response_msg_common)
    }

    fun getNobelPrizes(onRequestComplete: OnRequestComplete<WrapperPrizes>) {
        apiService.getNobelPrizes()
            .enqueue(object : ResponseHandler<WrapperPrizes>(onRequestComplete) {
                @Throws(Exception::class)
                override fun onSuccess(response: Response<WrapperPrizes>) {
                    if (response.code() == RESULT_OK)
                        onRequestComplete.onSuccess(response.body()!!)
                    else
                        onRequestComplete.onFailure(showGeneralizedError(), response.code())
                }
            })
    }

    companion object {
        private const val RESULT_OK = 200
        private const val RESULT_CREATED = 201
        private const val HTTP_UNAUTHORIZED = 401
        private lateinit var instance: APIHelper

        fun refreshBase() {
            resetField(this, "instance")
        }

        @Synchronized
        fun init(app: App): APIHelper {
            return if (Companion::instance.isInitialized) {
                instance
            } else {
                instance = APIHelper()
                instance.setApplication(app)
                instance
            }
        }
    }
}

fun resetField(target: Any, fieldName: String) {
    val field = target.javaClass.getDeclaredField(fieldName)
    with(field) {
        isAccessible = true
        set(target, null)
    }
}
