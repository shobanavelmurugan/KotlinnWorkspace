package com.shobanasha.roomdatabase.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.shobanasha.roomdatabase.data.dao.WordDao
import com.shobanasha.roomdatabase.data.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: String) = withContext(Dispatchers.IO) {
        wordDao.insert(Word(word))
    }

    @WorkerThread
    suspend fun deleteAllWords() = withContext(Dispatchers.IO) {
        wordDao.deleteAll()
    }
}