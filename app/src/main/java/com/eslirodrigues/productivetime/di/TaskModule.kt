package com.eslirodrigues.productivetime.di

import android.app.Application
import com.eslirodrigues.productivetime.TaskDatabase
import com.eslirodrigues.productivetime.data.datasource.TaskDataSourceImpl
import com.eslirodrigues.productivetime.data.repository.TaskRepository
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Singleton
    @Provides
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = TaskDatabase.Schema,
            context = app,
            name = "task.db"
        )
    }

    @Singleton
    @Provides
    fun provideTaskDataSource(driver: SqlDriver): TaskDataSourceImpl {
        return TaskDataSourceImpl(TaskDatabase(driver))
    }

    @Singleton
    @Provides
    fun provideTaskRepository(taskDataSourceImpl: TaskDataSourceImpl): TaskRepository {
        return TaskRepository(taskDataSourceImpl)
    }
}