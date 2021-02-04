/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.shobanasha.navigationGraph.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.shobanasha.navigationGraph.extension.Event
import com.shobanasha.navigationGraph.extension.getOrEmpty
import com.shobanasha.navigationGraph.extension.urlEncode
import com.shobanasha.navigationGraph.helper.NotificationHelper
import com.shobanasha.navigationGraph.helper.SharedPreferenceHelper
import com.shobanasha.navigationGraph.model.FragmentType
import com.shobanasha.navigationGraph.model.Letter
import com.shobanasha.navigationGraph.model.LetterRepository
import java.util.*

class LettersViewModel(val app: Application) : AndroidViewModel(app) {

    val sentLettersLiveData: MediatorLiveData<List<Letter>> = MediatorLiveData()
    val inboxLettersLiveData: MediatorLiveData<List<Letter>> = MediatorLiveData()
    val toastLiveData: MutableLiveData<Event<String>> = MutableLiveData()

    var loading = ObservableField(View.GONE)

    var recipient = ObservableField("")
    var title = ObservableField("")
    var description = ObservableField("")
    var ps = ObservableField("")

    var profileName = ObservableField("")
    var profileEmail = ObservableField("")

    private val gson by lazy { Gson() }
    private val notificationHelper by lazy { NotificationHelper(app) }
    private val sharedPreferenceHelper by lazy { SharedPreferenceHelper(app) }
    private val letterRepository by lazy { LetterRepository(app) }

    fun saveProfile() {
        sharedPreferenceHelper.saveProfile(profileName.getOrEmpty(), profileEmail.getOrEmpty())
    }

    fun loadProfile() {
        val profile = sharedPreferenceHelper.getProfile()
        profileName.set(profile.name)
        profileEmail.set(profile.email)
    }

    fun sendLetterWithDeeplink() {
        val letter = handleSend()
        Log.i(
            "sendLetterWithDeeplink",
            "http://www.loveletter.com/letter/${gson.toJson(letter).urlEncode()}"
        )
        toastLiveData.postValue(Event("You can find letter URL in logcat"))
    }

    fun sendPushNotification() {
        val letter = handleSend()
        notificationHelper.sendLocalNotification(letter)
    }

    fun saveLetterToInbox(letter: Letter) {
        letterRepository.upsertInbox(letter)
        loadInboxLetters()
    }

    fun deleteLetter(letter: Letter, fragmentType: FragmentType) {
        letterRepository.delete(letter)
        when (fragmentType) {
            FragmentType.INBOX -> loadInboxLetters()
            FragmentType.SENT -> loadSentLetters()
        }
    }

    fun hasFullProfile(): Boolean {
        val profile = sharedPreferenceHelper.getProfile()
        return profile.name.isNotEmpty() && profile.email.isNotEmpty()
    }

    fun loadSentLetters() {
        sentLettersLiveData.addSource(letterRepository.getSent(), sentLettersLiveData::postValue)
    }

    fun loadInboxLetters() {
        inboxLettersLiveData.addSource(letterRepository.getInbox(), inboxLettersLiveData::postValue)
    }

    fun closeDb() {
        letterRepository.close()
    }

    private fun handleSend(): Letter {
        val letter = buildLetterToSend()
        letterRepository.insertSent(letter)
        loadSentLetters()
        clearLetterFields()
        return letter
    }

    private fun clearLetterFields() {
        recipient.set("")
        title.set("")
        description.set("")
        ps.set("")
    }

    private fun buildLetterToSend(): Letter {
        val letter = Letter(title.getOrEmpty(), description.getOrEmpty(), ps.getOrEmpty())
        letter.to = recipient.getOrEmpty()
        letter.sentAt = Date().time

        val profile = sharedPreferenceHelper.getProfile()
        letter.from = profile.email
        letter.fromName = profile.name
        return letter
    }

}
