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

package com.shobanasha.navigationGraph.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.*

class LetterRepository(val application: Application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "love-letter"
    ).build()

    fun getInbox(): LiveData<List<Letter>> {
        return db.letterDao().getInbox()
    }

    fun getSent(): LiveData<List<Letter>> {
        return db.letterDao().getSent()
    }

    fun insertSent(letter: Letter) {
        letter.sentAt = Date().time
        AsyncTask.execute {
            db.letterDao().insert(letter)
        }
    }

    fun upsertInbox(letter: Letter) {
        letter.receivedAt = Date().time
        AsyncTask.execute {
            val id = db.letterDao().insert(letter)
            if (id == -1L) db.letterDao().update(letter)
        }
    }

    fun delete(letter: Letter) {
        AsyncTask.execute {
            db.letterDao().delete(letter)
        }
    }

    fun close() {
        if (db.isOpen) db.close()
    }
}
