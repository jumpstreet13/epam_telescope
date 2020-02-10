package com.abocha.epamtelescope.pictures

import android.app.Activity
import androidx.fragment.app.Fragment
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData
import com.miguelbcr.ui.rx_paparazzo2.entities.Response
import com.miguelbcr.ui.rx_paparazzo2.entities.size.OriginalSize
import io.reactivex.Observable
import java.io.File

class PhotoHelper(
    private val fragment: Fragment,
    private val mimeTypes: Array<String> = arrayOf("image/jpeg", "image/png")
) {

    private val size by lazy { OriginalSize() }

    fun getFromCamera(): Observable<File> =
        RxPaparazzo.single(fragment)
            .size(size)
            .usingCamera()
            .file()

    fun getFromGallery(): Observable<File> =
        RxPaparazzo.single(fragment)
            .size(size)
            .setMultipleMimeType(*mimeTypes)
            .usingGallery()
            .file()

    fun getFromGalleryMultiple(): Observable<List<File>> =
        RxPaparazzo.multiple(fragment)
            .size(size)
            .setMultipleMimeType(*mimeTypes)
            .useDocumentPicker()
            .usingFiles()
            .fileList()

    private fun <T> Observable<Response<T, FileData>>.file(): Observable<File> =
        flatMap { response ->
            response.handleResult {
                it.data()?.file?.let { Observable.just(it) } ?: Observable.empty()
            }
        }

    private fun <T> Observable<Response<T, List<FileData>>>.fileList(): Observable<List<File>> =
        flatMap { response ->
            response.handleResult {
                val files = mutableListOf<File>()
                response.data()?.forEach { it.file?.let { file -> files.add(file) } }
                if (files.isNotEmpty()) Observable.just(files) else Observable.empty()
            }
        }

    private fun <T, I, O> Response<T, I>.handleResult(func: (Response<T, I>) -> Observable<O>): Observable<O> {
        return when (resultCode()) {
            RxPaparazzo.RESULT_DENIED_PERMISSION_NEVER_ASK,
            RxPaparazzo.RESULT_DENIED_PERMISSION -> Observable.error(PermissionsDeniedException())
            Activity.RESULT_OK -> func(this)
            Activity.RESULT_CANCELED -> Observable.empty()
            else -> Observable.empty()
        }
    }
}

class PermissionsDeniedException : Exception()
