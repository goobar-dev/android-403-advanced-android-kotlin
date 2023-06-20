package dev.goobar.advancedandroiddemo.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.goobar.advancedandroiddemo.data.RepoEntity
import dev.goobar.advancedandroiddemo.db.RepoDao
import dev.goobar.advancedandroiddemo.network.GitHubFeaturedReposService

@HiltWorker
class FeaturedReposSyncWorker @AssistedInject constructor(
    private val featuredReposService: GitHubFeaturedReposService,
    private val repoDao: RepoDao,
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        repoDao.save(
            featuredReposService
                .getFeaturedAndroidRepos().items
                .map { repo -> RepoEntity(repo.name, repo.displayName, repo.description, repo.createdBy) }
        )

        return Result.success()
    }
}