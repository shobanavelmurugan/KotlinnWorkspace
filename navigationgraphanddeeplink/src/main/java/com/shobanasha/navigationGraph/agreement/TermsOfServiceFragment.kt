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

package com.shobanasha.navigationGraph.agreement

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import com.shobanasha.navigationGraph.R
import kotlinx.android.synthetic.main.fragment_privacy_policy.*

class TermsOfServiceFragment : Fragment(R.layout.fragment_terms_of_service) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = "<h3>1. Terms</h3>\n" +
                "<p>By accessing our app, Love Letter, you are agreeing to be bound by these terms of service, all applicable laws and regulations, and agree that you are responsible for compliance with any applicable local laws. If you do not agree with any of these terms, you are prohibited from using or accessing Love Letter. The materials contained in Love Letter are protected by applicable copyright and trademark law.</p>\n" +
                "<h3>2. Use License</h3>\n" +
                "<ol type=\"a\">\n" +
                "   <li>Permission is granted to temporarily download one copy of Love Letter per device for personal, non-commercial transitory viewing only. This is the grant of a license, not a transfer of title, and under this license you may not:\n" +
                "   <ol type=\"i\">\n" +
                "       <li>modify or copy the materials;</li>\n" +
                "       <li>use the materials for any commercial purpose, or for any public display (commercial or non-commercial);</li>\n" +
                "       <li>attempt to decompile or reverse engineer any software contained in Love Letter;</li>\n" +
                "       <li>remove any copyright or other proprietary notations from the materials; or</li>\n" +
                "       <li>transfer the materials to another person or \"mirror\" the materials on any other server.</li>\n" +
                "   </ol>\n" +
                "    </li>\n" +
                "   <li>This license shall automatically terminate if you violate any of these restrictions and may be terminated by RayWenderlich.com at any time. Upon terminating your viewing of these materials or upon the termination of this license, you must destroy any downloaded materials in your possession whether in electronic or printed format.</li>\n" +
                "</ol>\n" +
                "<h3>3. Disclaimer</h3>\n" +
                "<ol type=\"a\">\n" +
                "   <li>The materials within Love Letter are provided on an 'as is' basis. RayWenderlich.com makes no warranties, expressed or implied, and hereby disclaims and negates all other warranties including, without limitation, implied warranties or conditions of merchantability, fitness for a particular purpose, or non-infringement of intellectual property or other violation of rights.</li>\n" +
                "   <li>Further, RayWenderlich.com does not warrant or make any representations concerning the accuracy, likely results, or reliability of the use of the materials on its website or otherwise relating to such materials or on any sites linked to Love Letter.</li>\n" +
                "</ol>\n" +
                "<h3>4. Limitations</h3>\n" +
                "<p>In no event shall RayWenderlich.com or its suppliers be liable for any damages (including, without limitation, damages for loss of data or profit, or due to business interruption) arising out of the use or inability to use Love Letter, even if RayWenderlich.com or a RayWenderlich.com authorized representative has been notified orally or in writing of the possibility of such damage. Because some jurisdictions do not allow limitations on implied warranties, or limitations of liability for consequential or incidental damages, these limitations may not apply to you.</p>\n" +
                "<h3>5. Accuracy of materials</h3>\n" +
                "<p>The materials appearing in Love Letter could include technical, typographical, or photographic errors. RayWenderlich.com does not warrant that any of the materials on Love Letter are accurate, complete or current. RayWenderlich.com may make changes to the materials contained in Love Letter at any time without notice. However RayWenderlich.com does not make any commitment to update the materials.</p>\n" +
                "<h3>6. Links</h3>\n" +
                "<p>RayWenderlich.com has not reviewed all of the sites linked to its app and is not responsible for the contents of any such linked site. The inclusion of any link does not imply endorsement by RayWenderlich.com of the site. Use of any such linked website is at the user's own risk.</p>\n" +
                "<h3>7. Modifications</h3>\n" +
                "<p>RayWenderlich.com may revise these terms of service for its app at any time without notice. By using Love Letter you are agreeing to be bound by the then current version of these terms of service.</p>\n" +
                "<h3>8. Governing Law</h3>\n" +
                "<p>These terms and conditions are governed by and construed in accordance with the laws of USA and you irrevocably submit to the exclusive jurisdiction of the courts in that State or location.</p>\n"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            textView.text = Html.fromHtml(text)
        }
    }
}

