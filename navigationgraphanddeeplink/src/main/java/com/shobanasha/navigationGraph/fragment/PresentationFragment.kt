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

package com.shobanasha.navigationGraph.fragment

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.google.gson.Gson
import com.shobanasha.navigationGraph.R
import com.shobanasha.navigationGraph.adapter.LetterPagerAdapter
import com.shobanasha.navigationGraph.extension.urlDecode
import com.shobanasha.navigationGraph.model.Letter
import com.shobanasha.navigationGraph.viewmodel.LettersViewModel
import kotlinx.android.synthetic.main.fragment_presentation.*
import kotlinx.android.synthetic.main.letter_page_description.view.*
import kotlinx.android.synthetic.main.letter_page_ps.view.*
import kotlinx.android.synthetic.main.letter_page_title.view.*

class PresentationFragment : Fragment(R.layout.fragment_presentation) {

    private val lettersViewModel: LettersViewModel by navGraphViewModels(R.id.nav_graph)
    private val args: PresentationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val letter = Gson().fromJson(
            args.letter.urlDecode(),
            Letter::class.java
        )
        lettersViewModel.saveLetterToInbox(letter)

        viewPager.adapter = context?.let {
            LetterPagerAdapter(
                it, letter
            )
        }

        viewPager.setPageTransformer(false) { page, position ->
            page.ivStar?.apply {
                scaleX = (1 - position)
                scaleY = (1 - position)
                translationX = page.width * position * 0.1f
                alpha = 1 - position
            }

            page.ivFlower?.apply {
                translationY = -position * page.height
                translationX = page.width * position * 0.1f
            }

            arrayOf(
                page.ivDonut1,
                page.ivDonut2,
                page.ivDonut3
            ).forEachIndexed { index, imageView ->
                imageView?.apply {
                    val layoutParams = this.layoutParams as ConstraintLayout.LayoutParams
                    layoutParams.circleAngle = 120 * index + 360 * position
                    layoutParams.circleAngle %= 360
                    layoutParams.circleRadius = Math.abs(300 * (1 - position)).toInt()
                    this.layoutParams = layoutParams
                }
            }

            page.ivHearts?.apply {
                translationX = position * page.width * 2
                scaleX = (1 - position)
                scaleY = (1 - position)
            }
            page.ivCandles?.apply {
                translationX = position * page.width * 0.5f
            }

            page.ivAndroid?.apply {
                alpha = 1 - position
            }
        }
    }
}
