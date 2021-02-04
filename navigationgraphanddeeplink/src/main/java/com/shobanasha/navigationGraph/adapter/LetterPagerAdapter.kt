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

package com.shobanasha.navigationGraph.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.shobanasha.navigationGraph.R
import com.shobanasha.navigationGraph.extension.getString
import com.shobanasha.navigationGraph.model.Letter
import kotlinx.android.synthetic.main.letter_page_description.view.*
import kotlinx.android.synthetic.main.letter_page_ps.view.*
import kotlinx.android.synthetic.main.letter_page_title.view.*

class LetterPagerAdapter(
    val context: Context,
    val letter: Letter
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout =
            inflater.inflate(
                LetterPage.values()[position].layoutResId,
                container,
                false
            ) as ViewGroup

        when (position) {
            LetterPage.PAGE_TITLE.ordinal -> {
                layout.tvTitle.text = letter.title
                layout.tvFrom.text = container.getString(R.string.from_email, letter.from ?: "")
                layout.tvTo.text = container.getString(R.string.to_email, letter.to ?: "")
            }
            LetterPage.PAGE_DESCRIPTION.ordinal -> layout.tvDescription.text = letter.description
            LetterPage.PAGE_PS.ordinal -> layout.tvPs.text = letter.ps
        }
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return LetterPage.values().size
    }

    private enum class LetterPage(val layoutResId: Int) {
        PAGE_TITLE(R.layout.letter_page_title),
        PAGE_DESCRIPTION(R.layout.letter_page_description),
        PAGE_PS(R.layout.letter_page_ps)
    }

}
