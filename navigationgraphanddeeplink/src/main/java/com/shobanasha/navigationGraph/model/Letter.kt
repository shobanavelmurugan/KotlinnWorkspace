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

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Letter(@PrimaryKey val uid: Long = Date().time) : Parcelable {

    constructor(title: String = "", description: String = "", ps: String = "") : this() {
        this.title = title
        this.description = description
        this.ps = ps
    }

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "description")
    var description: String = ""

    @ColumnInfo(name = "ps")
    var ps: String = ""

    @ColumnInfo(name = "to")
    var to: String? = null

    @ColumnInfo(name = "from")
    var from: String? = null

    @ColumnInfo(name = "fromName")
    var fromName: String? = null

    @ColumnInfo(name = "saved_at")
    var savedAt: Long? = null

    @ColumnInfo(name = "sent_at")
    var sentAt: Long? = null

    @ColumnInfo(name = "received_at")
    var receivedAt: Long? = null

    constructor(parcel: Parcel) : this(parcel.readLong()) {
        title = parcel.readString()!!
        description = parcel.readString()!!
        ps = parcel.readString()!!
        to = parcel.readString()
        from = parcel.readString()
        fromName = parcel.readString()
        savedAt = parcel.readValue(Long::class.java.classLoader) as? Long
        sentAt = parcel.readValue(Long::class.java.classLoader) as? Long
        receivedAt = parcel.readValue(Long::class.java.classLoader) as? Long
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(uid)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(ps)
        parcel.writeString(to)
        parcel.writeString(from)
        parcel.writeString(fromName)
        parcel.writeValue(savedAt)
        parcel.writeValue(sentAt)
        parcel.writeValue(receivedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Letter> {
        override fun createFromParcel(parcel: Parcel): Letter {
            return Letter(parcel)
        }

        override fun newArray(size: Int): Array<Letter?> {
            return arrayOfNulls(size)
        }
    }

}
