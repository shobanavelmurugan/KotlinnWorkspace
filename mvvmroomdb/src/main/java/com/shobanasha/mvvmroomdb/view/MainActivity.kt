package com.shobanasha.mvvmroomdb.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import com.shobanasha.mvvmroomdb.R
import com.shobanasha.mvvmroomdb.RoomDbApplication
import com.shobanasha.mvvmroomdb.viewmodel.LoginViewModel
import com.shobanasha.mvvmroomdb.worker.DbWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var loginViewModel: LoginViewModel

    lateinit var context: Context

    lateinit var strUsername: String
    lateinit var strPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnAddLogin.setOnClickListener {

            strUsername = txtUsername.text.toString().trim()
            strPassword = txtPassword.text.toString().trim()

            if (strPassword.isEmpty()) {
                txtUsername.error = "Please enter the username"
            } else if (strPassword.isEmpty()) {
                txtPassword.error = "Please enter the username"
            } else {
                loginViewModel.insertData(context, strUsername, strPassword)
                /*lblInsertResponse.text = "Inserted Successfully"*/
            }
        }

        btnReadLogin.setOnClickListener {
            try {

                strUsername = txtUsername.text.toString().trim()

                /* loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {

                if (it == null) {
                    lblReadResponse.text = "Data Not Found"
                    lblUseraname.text = "- - -"
                    lblPassword.text = "- - -"
                }
                else {
                    lblUseraname.text = it.Username
                    lblPassword.text = it.Password

                    lblReadResponse.text = "Data Found Successfully"
                }
            })*/
                val dbWorkRequest = OneTimeWorkRequestBuilder<DbWorker>().build()
                RoomDbApplication.workManagerInstance!!.enqueue(dbWorkRequest)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}