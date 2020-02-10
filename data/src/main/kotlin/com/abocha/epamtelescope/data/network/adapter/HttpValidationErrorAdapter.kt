package com.abocha.epamtelescope.data.network.adapter

import com.abocha.epamtelescope.data.network.models.errors.HttpKeySelector
import com.abocha.epamtelescope.exceptions.FieldType
import com.abocha.epamtelescope.exceptions.UnknownErrorException
import com.abocha.epamtelescope.exceptions.ValidationException
import com.abocha.epamtelescope.rxerroradapter.ErrorAdapter
import com.abocha.epamtelescope.rxerroradapter.HttpResponseError
import org.json.JSONObject

private const val INDEX_FIRST = 0

class HttpValidationErrorAdapter : ErrorAdapter<HttpResponseError> {
    override fun invoke(response: HttpResponseError): Throwable? =
        mapError(response)

    private val errorFieldTypeMap: Map<String, FieldType> = mutableMapOf(
        "email" to FieldType.EMAIL,
        "password" to FieldType.PASSWORD,
        "old_password" to FieldType.OLD_PASSWORD,
        "new_password_1" to FieldType.NEW_PASSWORD,
        "new_password_2" to FieldType.CONFIRM_PASSWORD,
        "code" to FieldType.CODE
    )

    private fun mapError(response: HttpResponseError) =
        response.errorBodyString?.let { errorBody ->
            val jsonObject = JSONObject(errorBody)
            createValidationError(jsonObject)
        } ?: UnknownErrorException

    //parse validation error and creates map of errors
    private fun createValidationError(jsonObject: JSONObject): ValidationException {
        val errorMap = mutableMapOf<FieldType, String>()
        jsonObject.keys().forEach { key ->
            if (key != HttpKeySelector.ERROR_CODE_KEY) {
                val fieldType = resolveFieldType(key)
                val message = jsonObject.optJSONArray(key)?.getString(INDEX_FIRST) ?: ""
                if (fieldType != null) {
                    errorMap[fieldType] = message
                } else {
                    //todo logging
                }
            }
        }
        return ValidationException(errorMap)
    }

    private fun resolveFieldType(fieldName: String): FieldType? =
        errorFieldTypeMap[fieldName]

}
