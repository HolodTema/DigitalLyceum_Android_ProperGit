package com.example.lyceumapp.json

import android.content.Context
import android.content.res.Resources
import com.example.lyceumapp.CantGenerateObjectsFromJsonException
import com.example.lyceumapp.IncorrectLyceumClassGradeException
import com.example.lyceumapp.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class JsonManager() {
    companion object{
        private var instance: JsonManager? = null

        fun getInstance() = instance ?: JsonManager()
    }

//    companion object{
//        private var instance: JsonManager? = null
//        suspend fun getInstance(context: Context, resources: Resources, listener: OnJsonUnpackedListener) = coroutineScope{
//            val deferred: Deferred<JsonManager> = async{
//                if(instance==null) {
//                    val json = resources.openRawResource(R.raw.schedule_lessons).bufferedReader().use {
//                        it.readText()
//                    }
//                    val moshi = Moshi.Builder()
//                        .addLast(KotlinJsonAdapterFactory())
//                        .build()
//                    val jsonAdapter = moshi.adapter(LessonsRoot::class.java)
//                    instance = JsonManager(jsonAdapter.fromJson(json) ?: throw CantGenerateObjectsFromJsonException())
//                }
//                instance!!
//            }
//            listener.onJsonUnpacked(deferred.await())
//        }
//    }
//
//    fun getLyceumClassObj(grade: String): LyceumClass {
//        if(jsonRoot.classes!=null) {
//            var result: LyceumClass? = null
//            for(e in jsonRoot.classes) {
//                if(e.className.lowercase()==grade.lowercase()) {
//                    result = e
//                    break
//                }
//            }
//            if(result!=null) return result
//            else throw IncorrectLyceumClassGradeException()
//        }
//        else throw CantGenerateObjectsFromJsonException()
//    }
//
//    interface OnJsonUnpackedListener{
//        fun onJsonUnpacked(instance: JsonManager)
//    }


}
