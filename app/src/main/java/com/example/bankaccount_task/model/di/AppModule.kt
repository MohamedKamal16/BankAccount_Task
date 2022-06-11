package com.example.bankaccount_task.model.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.bankaccount_task.model.local.db.BankOpenHelper
import com.example.bankaccount_task.model.local.db.DatabaseDataWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = BankOpenHelper(context)

    /**
    The db in constructor gonna be get from the return of first fun(provideDatabase)
     */
    @Singleton
    @Provides
    fun provideWorker(db: BankOpenHelper) = DatabaseDataWorker(db)


}