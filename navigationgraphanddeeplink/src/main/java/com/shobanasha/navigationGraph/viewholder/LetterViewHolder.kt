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

package com.shobanasha.navigationGraph.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shobanasha.navigationGraph.R
import com.shobanasha.navigationGraph.extension.getString
import com.shobanasha.navigationGraph.helper.DateTimeHelper
import com.shobanasha.navigationGraph.model.FragmentType
import com.shobanasha.navigationGraph.model.Letter
import kotlinx.android.synthetic.main.view_holder_letter.view.*

class LetterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.view_holder_letter, parent, false)) {

    private val dateTimeHelper = DateTimeHelper()

    fun bind(letter: Letter, fragmentType: FragmentType) {
        itemView.tvTitle.text = letter.title
        when (fragmentType) {
            FragmentType.SENT -> {
                itemView.tvEmail.text = itemView.getString(R.string.to_email, letter.to ?: "")
                itemView.tvDate.text = dateTimeHelper.parse(letter.sentAt)
            }
            FragmentType.INBOX -> {
                itemView.tvEmail.text = itemView.getString(R.string.from_email, letter.from ?: "")
                itemView.tvDate.text = dateTimeHelper.parse(letter.receivedAt)
            }
        }
    }
}
